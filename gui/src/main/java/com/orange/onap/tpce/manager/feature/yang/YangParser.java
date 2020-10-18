package com.orange.onap.tpce.manager.feature.yang;


import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import org.opendaylight.jsonrpc.impl.JsonConverter;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.schema.ContainerNode;
import org.opendaylight.yangtools.yang.data.api.schema.DataContainerChild;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNodes;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.codec.xml.XmlParserStream;
import org.opendaylight.yangtools.yang.data.impl.schema.ImmutableNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.NormalizedNodeResult;
import org.opendaylight.yangtools.yang.data.util.DataSchemaContextTree;
import org.opendaylight.yangtools.yang.model.api.ContainerSchemaNode;
import org.opendaylight.yangtools.yang.model.api.Module;
import org.opendaylight.yangtools.yang.model.api.SchemaContext;
import org.opendaylight.yangtools.yang.model.repo.api.YangTextSchemaSource;
import org.opendaylight.yangtools.yang.model.util.SchemaContextUtil;
import org.opendaylight.yangtools.yang.parser.rfc7950.reactor.RFC7950Reactors;
import org.opendaylight.yangtools.yang.parser.rfc7950.repo.YangStatementStreamSource;
import org.opendaylight.yangtools.yang.parser.stmt.reactor.CrossSourceStatementReactor;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.InputStream;
import java.util.Optional;

public class YangParser {
//    public void parse() throws Exception{
//
//        org.opendaylight.yangtools.yang.model.parser.api.YangParser parser =  new YangParserFactoryImpl().createParser( );
//
//
//SchemaSourceRepresentation yangSource = YangTextSchemaSource.forResource("C:\\Users\\bcxt1693\\WorkSpace\\Orange\\onap\\yang-tree-example\\yang\\org-openroadm-network-topology.yang");
//parser.addSource(yangSource);
//        SchemaContext schema=  parser.buildSchemaContext( );
//        System.out.println(schema.toString());
//
//
//    }


    public static void main(String[] a) throws Exception {
        // org.opendaylight.yangtools.yang.model.parser.api.YangParser parser =  new YangParserFactoryImpl().createParser( );parser.buildEffectiveModel().findModules("").
//   java.io.File resouceFile = new File("C:\\Users\\bcxt1693\\WorkSpace\\Orange\\onap\\yang-tree-example\\yang\\org-openroadm-network-topology.yang");
//
//        SchemaSourceRepresentation yangSource = YangTextSchemaSource.forFile(resouceFile);
//        parser.addLibSource(yangSource);
//       // parser.addSource(yangSource);
//        SchemaContext schema=  parser.buildSchemaContext( );
//        System.out.println("Hello:"+schema.toString());
//
        // YangTextSchemaContextResolver yangContextResolver = YangTextSchemaContextResolver.create("yang-context-resolver");
//
//        //yangContextResolver.registerSource(new URL("file:////C:\\Users\\bcxt1693\\WorkSpace\\Orange\\onap\\onap-tpce-manager\\target\\classes\\com\\orange\\onap\\tpce\\manager\\yang\\org-openroadm-network-topology.yang"));
//
//        yangContextResolver.registerSource(new URL("http://localhost:8080/yang/org-openroadm-network-topology.yang"));
//
//        Optional<SchemaContext> sc = yangContextResolver.getSchemaContext();
//
//        YangTextSchemaSource yangTextSchemaSource = YangTextSchemaSource.forFile(new File("C:\\Users\\bcxt1693\\WorkSpace\\Orange\\onap\\onap-tpce-manager\\target\\classes\\com\\orange\\onap\\tpce\\manager\\yang\\org-openroadm-network-topology.yang"));
//        StatementStreamSource yangModuleSource = YangStatementStreamSource.create(yangTextSchemaSource);
//
//
//
//        YangTextSchemaSource yangTextSchemaSource2 = YangTextSchemaSource.forFile(new File("C:\\Users\\bcxt1693\\WorkSpace\\Orange\\onap\\onap-tpce-manager\\target\\classes\\com\\orange\\onap\\tpce\\manager\\yang\\openconfig-extensions.yang"));
//        StatementStreamSource yangModuleSource2 = YangStatementStreamSource.create(yangTextSchemaSource2);

        ///openconfig-extensions.yang

//        CrossSourceStatementReactor.Builder builder  = CrossSourceStatementReactor.builder();
//        List<YangVersion> versions  = new ArrayList<>();
//       versions.add(YangVersion.VERSION_1);
//        versions.add(YangVersion.VERSION_1_1);
//        CrossSourceStatementReactor cross= RFC7950Reactors.defaultReactor();
//
//        CrossSourceStatementReactor.BuildAction reactor = cross.newBuild();
//        reactor.addSources(yangModuleSource);
//        //reactor.addSources(yangModuleSource2);
//        SchemaContext sc = reactor.buildEffective();


        final File ctrlFolder = new File("C:\\Users\\bcxt1693\\WorkSpace\\Orange\\onap\\onap-tpce-manager\\src\\main\\yang");
//
//        /*YangTextSchemaContextResolver yangContextResolver = YangTextSchemaContextResolver.create("yang-context-resolver");
//
//
//
//
//        for (final File fileEntry : ctrlFolder.listFiles()) {
//            if (fileEntry.isFile()) {
//                yangContextResolver.registerSource(fileEntry.toURI().toURL());
//            }
//        }
//
//        Optional<SchemaContext> sc = yangContextResolver.getSchemaContext();
//        System.out.println("Hello:" + sc.toString());*/
//
        CrossSourceStatementReactor.BuildAction reactor = RFC7950Reactors.defaultReactor().newBuild();


        for (final File fileEntry : ctrlFolder.listFiles()) {
            reactor.addSource(
                    YangStatementStreamSource.create(
                            YangTextSchemaSource.forFile(fileEntry)
                    )
            );
        }

        SchemaContext schemaContext = reactor.buildEffective();
        DataSchemaContextTree tree = DataSchemaContextTree.from(schemaContext);

        System.out.println("DataSchemaContextTree" + tree);


        //SchemaContext schemaContext  = YangParserTestUtils.parseYangFiles(Arrays.asList(ctrlFolder.listFiles()) );

        //YangParserFactoryImpl

        //parseYangResourceDirectory("C:\\Users\\bcxt1693\\WorkSpace\\Orange\\onap\\onap-tpce-manager\\src\\main\\yang");

        //List<Module> modules  = schemaContext.getModules().stream().filter(module -> module.getName().equalsIgnoreCase("org-openroadm-network")).collect(Collectors.toList());
//        System.out.println("ALl modules");
//        schemaContext.getModules().stream().forEach(module -> {
//            System.out.println(module.getName());
//            module.getAugmentations().stream().forEach(augment -> {
//                System.out.println("augment: " +augment.getTargetPath());
//            });
//        });
//        System.out.println("///////////////////////////////////");

//        System.out.println("All child nodes");
//        schemaContext.getDataDefinitions().stream().forEach(node -> {
//            System.out.println(node.getQName());
//            System.out.println(node.getPath());
//        });
        System.out.println("///////////////////////////////////");
//
        final Module ietfNetworkModule = schemaContext.findModules("ietf-network").iterator().next();
        schemaContext.getDataDefinitions();
//        System.out.println(ietfNetworkModule.getName());
//        final Module ietfNetworkTopologyModule = schemaContext.findModules("ietf-network-topology").iterator().next();
//        System.out.println(ietfNetworkTopologyModule.getName());
//        final Module openRoadmBaseModule = schemaContext.findModules("org-openroadm-common-network").iterator().next();
//        System.out.println(openRoadmBaseModule.getName());
//        /////////////////////////////////////////////
//        System.out.println("printing ietf module child nodes");
//        ietfNetworkModule.getChildNodes().stream().forEach(node -> {
//            System.out.println(node.getQName());
//            System.out.println(node.getPath());
////            System.out.println(node.getch;
//        });

//        DataSchemaNode dataSchemaNode = ietfNetworkModule.findDataChildByName(QName.create(ietfNetworkModule.getQNameModule(), "networks"), QName.create(ietfNetworkModule.getQNameModule(), "network"),
//                QName.create(ietfNetworkModule.getQNameModule(), "node"),
//                QName.create(ietfNetworkModule.getQNameModule(), "node-id")).orElse(null);
//        System.out.println("networks node: " + dataSchemaNode);
//
//        DataSchemaNode dataSchemaNode2 = ietfNetworkModule.findDataChildByName(QName.create(ietfNetworkModule.getQNameModule(), "networks"), QName.create(ietfNetworkModule.getQNameModule(), "network"),
//                QName.create(ietfNetworkModule.getQNameModule(), "node"),
//                QName.create(ietfNetworkTopologyModule.getQNameModule(), "termination-point")).orElse(null);
//        System.out.println("tp node: " + dataSchemaNode2);
//        DataSchemaNode dataSchemaNode3 = ietfNetworkModule.findDataChildByName(QName.create(ietfNetworkModule.getQNameModule(), "networks"), QName.create(ietfNetworkModule.getQNameModule(), "network"),
//                QName.create(ietfNetworkModule.getQNameModule(), "node"),
//                QName.create(ietfNetworkTopologyModule.getQNameModule(), "termination-point"),
//                QName.create(openRoadmBaseModule.getQNameModule(), "tp-type")).orElse(null);
//        System.out.println("tp-type node: " + dataSchemaNode3);
//        System.out.println("////////////////////////////////////////////");
        ///////////////////////////////////////////
//      "/nd:networks/nd:network/nd:node/nwt:termination-point/nwt:tp-type"
//        final SchemaPath path = SchemaPath.create(true, QName.create(openRoadmBaseModule.getQNameModule(), "networks"),
//                QName.create(openRoadmBaseModule.getQNameModule(), "network"), QName.create(openRoadmBaseModule.getQNameModule(), "node"),
//                QName.create(openRoadmBaseModule.getQNameModule(), "termination-point"), QName.create(openRoadmBaseModule.getQNameModule(), "tp-id"));
//        final SchemaNode foundNode = SchemaContextUtil.findDataSchemaNode(schemaContext, path);
//        System.out.println("node from schema context: " + foundNode);

        //////////////////////////////////////////
//        System.out.println("filter childs: " + schemaContext.getChildNodes().stream().filter(node -> node.getQName().getLocalName().equalsIgnoreCase("tp-type")).findFirst().orElse(null));
//
//        Optional<DataSchemaNode> tpTypeNode = schemaContext.findDataChildByName(QName.create(schemaContext.getQName(), "tp-type"));
//        System.out.println("tp-type" + tpTypeNode.orElse(null));
//
//
//
//        System.out.println(ietfNetworkModule.getQNameModule());
//        System.out.println("//////////////////////////");


        final ContainerSchemaNode parentSchemaNode = (ContainerSchemaNode) SchemaContextUtil.findNodeInSchemaContext(schemaContext,
                ImmutableList.of(QName.create(ietfNetworkModule.getQNameModule(), "networks")));

        System.out.println(parentSchemaNode);


        InputStream resourceAsStream = YangParser.class.getResourceAsStream("openroadm-topology-example-sample.xml");

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(resourceAsStream);

        NormalizedNodeResult result = new NormalizedNodeResult();
        NormalizedNodeStreamWriter streamWriter = ImmutableNormalizedNodeStreamWriter.from(result);

        XmlParserStream xmlParser = XmlParserStream.create(streamWriter, schemaContext, parentSchemaNode, false);
        xmlParser.parse(reader);

        NormalizedNode<?, ?> transformedInput = result.getResult();
        System.out.println("string tree: " + NormalizedNodes.toStringTree(transformedInput));


        ContainerNode bazContainer = (ContainerNode) transformedInput;
        System.out.println("parser result: " + bazContainer);

        Optional<DataContainerChild<? extends YangInstanceIdentifier.PathArgument, ?>> bazContainerChild = bazContainer.getChild(
                new YangInstanceIdentifier.NodeIdentifier(QName.create(ietfNetworkModule.getQNameModule(), "network")));
        System.out.println("parser result child: " + bazContainerChild.get());
//        YangModeledAnyXmlNode yangModeledAnyXmlNode = (YangModeledAnyXmlNode) bazContainerChild.get();

//        DataSchemaNode schemaOfAnyXmlData = yangModeledAnyXmlNode.getSchemaOfAnyXmlData();
//s
//        System.out.println("schemaOfAnyXmlData: " + schemaOfAnyXmlData);
        //YangInstanceIdentifier.NodeIdentifier n = (YangInstanceIdentifier.NodeIdentifier) transformedInput.getValue();

//        System.out.println(transformedInput.getNodeType());
//        System.out.println(transformedInput.getIdentifier());
//        System.out.println(transformedInput.getValue());
////        YangModeledAnyXmlNode yangModeledAnyXmlNode = (YangModeledAnyXmlNode)transformedInput;
//
//        System.out.println("transformedInput : "+transformedInput);

//        System.out.println("Shema for transformed data: " + yangModeledAnyXmlNode.getSchemaOfAnyXmlData());

//        ContainerNode node = (ContainerNode)transformedInput;
//        Collection<DataContainerChild<? extends YangInstanceIdentifier.PathArgument,?>> list = node.getValue();
//        System.out.println(list);
//
//        list.stream().forEach(mapNode -> {
//            System.out.println(mapNode.getNodeType() + "" + mapNode.getIdentifier());
//            Collection<DataContainerChild<? extends YangInstanceIdentifier.PathArgument,?>> value = (Collection<DataContainerChild<? extends YangInstanceIdentifier.PathArgument,?>> )mapNode.getValue();
//            System.out.println(value);
//        });


        JsonConverter jsonConverter = new JsonConverter(schemaContext);

        JsonObject testJson = jsonConverter.rpcConvert(parentSchemaNode.getPath(), (ContainerNode) transformedInput);
        System.out.println(testJson.toString());

    }
}
