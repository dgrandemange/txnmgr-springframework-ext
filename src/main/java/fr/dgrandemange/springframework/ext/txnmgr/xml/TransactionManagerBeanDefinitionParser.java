package fr.dgrandemange.springframework.ext.txnmgr.xml;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.parsing.ParseState;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import fr.dgrandemange.springframework.ext.txnmgr.factory.parsing.ParticipantEntry;
import fr.dgrandemange.txnmgr.model.ParticipantsGroup;
import fr.dgrandemange.txnmgr.model.ParticipantsGroupRegistry;

/**
 * @author dgrandemange
 * 
 */
public class TransactionManagerBeanDefinitionParser extends
		AbstractSingleBeanDefinitionParser {

	private static final String ID_ATTRIBUTE = "id";
	private static final String PARTICIPANT_ELEMENT = "participant";
	private static final String GROUP_ELEMENT = "group";
	public static final Object SUBFLOW_ELEMENT = "subflow";
	private static final String CLASS_ATTRIBUTE = "class";
	private static final String NAME_ATTRIBUTE = "name";
	public static final String FACTORY_METHOD_ATTRIBUTE = "factory-method";
	public static final String FACTORY_BEAN_ATTRIBUTE = "factory-bean";

	public static final String DEFAULT_FACTORY_METHOD_NAME = "create";

	private ParseState parseState;
	private ParserContext parserContext;

	@Override
	protected void doParse(Element element, ParserContext parserContext,
			BeanDefinitionBuilder builder) {
		this.parserContext = parserContext;
		this.parseState = new ParseState();

		String name = element.getAttribute(NAME_ATTRIBUTE);
		BeanDefinition grpRegistryDef = createGroupRegistryDefinition(name,
				element);

		AbstractBeanDefinition txMgrBeanDefinition = builder
				.getBeanDefinition();

		// Bean creation is delegated to a factory
		txMgrBeanDefinition.setFactoryBeanName(element
				.getAttribute(FACTORY_BEAN_ATTRIBUTE));

		if (element.hasAttribute(FACTORY_METHOD_ATTRIBUTE)) {
			txMgrBeanDefinition.setFactoryMethodName(element
					.getAttribute(FACTORY_METHOD_ATTRIBUTE));
		} else {
			txMgrBeanDefinition
					.setFactoryMethodName(DEFAULT_FACTORY_METHOD_NAME);
		}

		// Factory creation method expects 2 arguments :
		// * arg n°1 : transaction manager name
		// * arg n°2 : a ParticipantsGroupRegistry instance
		ConstructorArgumentValues cav = txMgrBeanDefinition
				.getConstructorArgumentValues();
		cav.addGenericArgumentValue(name);
		cav.addGenericArgumentValue(grpRegistryDef);
	}

	private BeanDefinition createGroupRegistryDefinition(String name,
			Element element) {
		ManagedMap<String, BeanDefinition> ptpGroupsMap = new ManagedMap<String, BeanDefinition>();

		String currentGroupName = ParticipantsGroupRegistry.GROUPS_REGISTRY__ROOT_GROUPNAME;
		ManagedList<BeanDefinition> rootGrpParticipants = registerGroup(
				ptpGroupsMap, currentGroupName);
		processGroup(ptpGroupsMap, element, currentGroupName,
				rootGrpParticipants);

		RootBeanDefinition grpRegistryDef = new RootBeanDefinition(
				ParticipantsGroupRegistry.class);
		ConstructorArgumentValues cav = grpRegistryDef
				.getConstructorArgumentValues();
		cav.addGenericArgumentValue(ptpGroupsMap);

		return grpRegistryDef;
	}

	protected ManagedList<BeanDefinition> registerGroup(
			ManagedMap<String, BeanDefinition> ptpGroupsMap, String groupName) {
		ManagedList<BeanDefinition> grpParticipants = new ManagedList<BeanDefinition>();

		BeanDefinitionBuilder rootGrpDefBuilder = BeanDefinitionBuilder
				.rootBeanDefinition(ParticipantsGroup.class);
		ConstructorArgumentValues constructorArgumentValues = rootGrpDefBuilder
				.getBeanDefinition().getConstructorArgumentValues();
		constructorArgumentValues.addGenericArgumentValue(groupName);
		constructorArgumentValues.addGenericArgumentValue(grpParticipants);

		ptpGroupsMap.put(groupName, rootGrpDefBuilder.getBeanDefinition());

		return grpParticipants;
	}

	protected void processGroup(
			ManagedMap<String, BeanDefinition> ptpGroupsMap, Element groupElt,
			String groupName, ManagedList<BeanDefinition> grpParticipants) {

		NodeList childNodes = groupElt.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				String localName = node.getLocalName();
				if (PARTICIPANT_ELEMENT.equals(localName)) {
					BeanDefinition ptpBeanDef = parseParticipant((Element) node);
					grpParticipants.add(ptpBeanDef);
				} else if (GROUP_ELEMENT.equals(localName)) {
					Element childGrpElt = (Element) node;
					String childGroupName = childGrpElt
							.getAttribute(NAME_ATTRIBUTE);
					grpParticipants = registerGroup(ptpGroupsMap,
							childGroupName);
					processGroup(ptpGroupsMap, childGrpElt, childGroupName,
							grpParticipants);
				} else if (SUBFLOW_ELEMENT.equals(localName)) {
					Element childSubflowElt = (Element) node;
					processGroup(ptpGroupsMap, childSubflowElt, null, null);
				}
			}
		}
	}

	private BeanDefinition parseParticipant(Element participantElement) {
		AbstractBeanDefinition participantDef = createParticipantBeanDefinition(
				participantElement, parserContext);
		String id = participantElement.getAttribute(ID_ATTRIBUTE);

		String participantBeanName = id;
		if (StringUtils.hasText(participantBeanName)) {
			parserContext.getRegistry().registerBeanDefinition(
					participantBeanName, participantDef);
		} else {
			participantBeanName = parserContext.getReaderContext()
					.registerWithGeneratedName(participantDef);
		}

		this.parseState.push(new ParticipantEntry(participantBeanName));

		try {
			BeanDefinitionParserDelegate delegate = parserContext.getDelegate();
			delegate.parsePropertyElements(participantElement, participantDef);
		} finally {
			this.parseState.pop();
		}

		return participantDef;
	}

	private AbstractBeanDefinition createParticipantBeanDefinition(
			Element participantElement, ParserContext parserContext) {
		String ptpclass = participantElement.getAttribute(CLASS_ATTRIBUTE);
		RootBeanDefinition participantDefinition = new RootBeanDefinition(
				ptpclass);

		participantDefinition.setSource(parserContext
				.extractSource(participantElement));

		return participantDefinition;
	}
	
/*
	private void register(BeanDefinitionRegistry registry, BeanDefinition bean,
			String name) {
		BeanDefinitionHolder holder = new BeanDefinitionHolder(bean, name);
		BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
	}
*/
}
