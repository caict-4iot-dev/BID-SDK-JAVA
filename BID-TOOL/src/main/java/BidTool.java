import cn.ac.caict.bid.utils.CheckTool;
import cn.bif.module.encryption.model.KeyType;
import org.apache.commons.cli.*;

public class BidTool {
    public static void Usage(){
        System.out.printf("Usage: java bid-tool.jar [OPTIONS]\n" +
                "OPTIONS:\n" +
                "  --help                                                                       display this help\n" +
                "  --version                                                                    get bid-sdk version\n" +
                "  --get-bid-and-keypair <keytype>                                              get bid and keypair\n" +
                "  --get-bid-and-keypair-with-chaincode <chaincode>  <keytype>                  get bid and keypair with chaincode\n" +
                "  --get-bid-by-publickey <public-key>                                          get bid from public key\n" +
                "  --get-pubkey-by-prikey <private-key>                                         get public key from private key\n" +
                "  --sign-data <private-key> <blob-data>                                        sign blob data\n" +
                "  --verify-signed-data <blob data> <signed data> <public key>                  verify signed data\n" +
                "  --check-bid <bid>                                                            check bid format\n" +
                "  --get-bid-template                                                           get bid template\n" +
                "  --create-bid <url> <bid data>                                                create bid document\n" +
                "  --is-valid-proof  <document>                                                 Verify the signature of the document content\n" +
                "  --bid-query-by-contract <url> <bid address>                                  BID Address contract query\n" +
                "  --resolver-bid <url> <bid address>                                           recursive resolver bid\n" +
                "  --query-transaction-info <url> <hash>                                        Query transaction information based on hash\n" +
                "  --get-bid-by-hash <url> <hash>                                               Get BID Address by hash\n"
                );
    }
    public static void main(String[] args) {
        CommandLineParser commandLineParser = new DefaultParser();
        Options options = new Options();
        options.addOption(null, "help", false, "display this help");
        options.addOption(null, "version", false, "get bid-sdk version");
        options.addOption(null, "get-bid-by-publickey", true, "get bid from public key");
        options.addOption(null, "get-pubkey-by-prikey", true, "get public key from private key");
        options.addOption(Option.builder(null)
                .longOpt("get-bid-and-keypair")
                .desc("get bid and keypair")
                .hasArg()
                .optionalArg(true)
                .numberOfArgs(1)
                .build());
        options.addOption(Option.builder()
                .longOpt("get-bid-and-keypair-with-chaincode")
                .desc("get-bid-and-keypair-with-chaincode")
                .hasArg()
                .optionalArg(true)
                .numberOfArgs(2)
                .build());
        options.addOption(Option.builder(null)
                .longOpt("sign-data")
                .desc("sign-data")
                .hasArg()
                .numberOfArgs(2)
                .build());
        options.addOption(Option.builder(null)
                .longOpt("verify-signed-data")
                .desc("verify signed data")
                .hasArg()
                .numberOfArgs(3)
                .build());
        options.addOption(null, "check-bid", true,"check bid format");
        options.addOption(null, "get-bid-template", false, "get bid template");
        options.addOption(Option.builder(null)
                .longOpt("create-bid")
                .desc("create bid document")
                .hasArg()
                .optionalArg(true)
                .numberOfArgs(2)
                .build());
        options.addOption(Option.builder(null)
                .longOpt("is-valid-proof")
                .desc("Verify the signature of the document content")
                .hasArg()
                .optionalArg(true)
                .numberOfArgs(1)
                .build());
        options.addOption(Option.builder(null)
                .longOpt("bid-query-by-contract")
                .desc("BID Address contract query")
                .hasArg()
                .optionalArg(true)
                .numberOfArgs(2)
                .build());
        options.addOption(Option.builder(null)
                .longOpt("resolver-bid")
                .desc("recursive resolver bid")
                .hasArg()
                .optionalArg(true)
                .numberOfArgs(2)
                .build());
        options.addOption(Option.builder(null)
                .longOpt("query-transaction-info")
                .desc("Query transaction information based on hash")
                .hasArg()
                .optionalArg(true)
                .numberOfArgs(2)
                .build());
        options.addOption(Option.builder(null)
                .longOpt("get-bid-by-hash")
                .desc("Get BID Address by hash")
                .hasArg()
                .optionalArg(true)
                .numberOfArgs(2)
                .build());
        CommandLine commandLine = null;
        try {
            commandLine = commandLineParser.parse(options, args);
        }catch (Exception e){
            Usage();
            System.exit(0);
        }
        if (commandLine.hasOption("help")) {
            Usage();
            System.exit(0);
        }
        if (commandLine.hasOption("version")) {
            BidService.getVersion();
            System.exit(0);
        }
        if (commandLine.hasOption("get-bid-and-keypair")) {
            String[] srgs = commandLine.getOptionValues("get-bid-and-keypair");
            if(null == srgs || srgs.length == 0){
                BidService.getBidAndKeypair(null);
                System.exit(0);
            }else if(srgs.length ==1){
                String keyTypeStr = srgs[0];
                KeyType keyType = null;
                if ("sm2".equals(keyTypeStr)) {
                    keyType = KeyType.SM2;
                } else if ("ed25519".equals(keyTypeStr)) {
                    keyType = KeyType.ED25519;
                } else {
                    System.out.println("The keytype is invalid,keytype need either sm2 or ed25519");
                    Usage();
                    System.exit(0);
                }
                BidService.getBidAndKeypair(null, keyType);
                System.exit(0);
            }else {
                System.out.println("The parameter is illegal,the parameter either null or keytype ");
                Usage();
                System.exit(0);
            }
        }
        if (commandLine.hasOption("get-bid-and-keypair-with-chaincode")) {
            String[] srgs = commandLine.getOptionValues("get-bid-and-keypair-with-chaincode");
            if(null == srgs){
                Usage();
                System.exit(0);
            }else if(srgs.length == 1 ){
                String chainCode = srgs[0];
                if(!CheckTool.isAc(chainCode)){
                    System.out.println("The chainCode is invalid");
                    Usage();
                    System.exit(0);
                }
                BidService.getBidAndKeypair(chainCode);
                System.exit(0);
            }else if(srgs.length == 2){
                String chainCode = srgs[0];
                String keyTypeStr = srgs[1];
                KeyType keyType = null;
                if(!CheckTool.isAc(chainCode)){
                    System.out.println("The chainCode is invalid");
                    Usage();
                    System.exit(0);
                }
                if ("sm2".equals(keyTypeStr)) {
                    keyType = KeyType.SM2;
                } else if ("ed25519".equals(keyTypeStr)) {
                    keyType = KeyType.ED25519;
                } else {
                    System.out.println("The keytype is invalid,keytype need either sm2 or ed25519");
                    Usage();
                    System.exit(0);
                }

                BidService.getBidAndKeypair(chainCode, keyType);
                System.exit(0);
            }else {
                System.out.println("The parameter is illegal,the parameter either chaincode or chaincode��keytype ");
                Usage();
                System.exit(0);
            }
        }
        if (commandLine.hasOption("get-bid-by-publickey")) {
            String publicKey = commandLine.getOptionValue("get-bid-by-publickey");
            BidService.getBidByPublicKey(publicKey);
            System.exit(0);
        }
        if (commandLine.hasOption("get-pubkey-by-prikey")) {
            String privateKey = commandLine.getOptionValue("get-pubkey-by-prikey");
            BidService.getPubkeyByPrekey(privateKey);
            System.exit(0);
        }
        if (commandLine.hasOption("sign-data")) {
            String[] srgs = commandLine.getOptionValues("sign-data");
            if (srgs.length != 2) {
                Usage();
                System.exit(0);
            }
            String privateKey = srgs[0];
            String msg = srgs[1];
            BidService.signedBlob(privateKey, msg);
            System.exit(0);
        }
        if (commandLine.hasOption("verify-signed-data")) {
            String[] srgs = commandLine.getOptionValues("verify-signed-data");
            if (srgs.length != 3) {
                Usage();
                System.exit(0);
            }
            String msg = srgs[0];
            String sig = srgs[1];
            String publicKey = srgs[2];

            BidService.verifysing(publicKey, msg, sig);
            System.exit(0);
        }
        if (commandLine.hasOption("check-bid")) {
            String bid = commandLine.getOptionValue("check-bid");
            BidService.checkBid(bid);
            System.exit(0);
        }
        if (commandLine.hasOption("get-bid-template")) {
            BidService.getBIDTemplate();
            System.exit(0);
        }
        if (commandLine.hasOption("create-bid")) {
            String[] srgs = commandLine.getOptionValues("create-bid");
            if (srgs.length != 2) {
                Usage();
                System.exit(0);
            }
            String url = srgs[0];
            String bid = srgs[1];
            BidService.createBidByTemplate(url,bid);
            System.exit(0);
        }
        if (commandLine.hasOption("is-valid-proof")) {
            String[] srgs = commandLine.getOptionValues("is-valid-proof");
            if (srgs.length != 1) {
                Usage();
                System.exit(0);
            }
            String bid = srgs[0];
            BidService.isValidProof(bid);
            System.exit(0);
        }
        if (commandLine.hasOption("bid-query-by-contract")) {
            String[] srgs = commandLine.getOptionValues("bid-query-by-contract");
            if (srgs.length != 2) {
                Usage();
                System.exit(0);
            }
            String url = srgs[0];
            String bidAddress = srgs[1];
            BidService.bidQueryByContract(url,bidAddress);
            System.exit(0);
        }
        if (commandLine.hasOption("resolver-bid")) {
            String[] srgs = commandLine.getOptionValues("resolver-bid");
            if (srgs.length != 2) {
                Usage();
                System.exit(0);
            }
            String url = srgs[0];
            String bidAddress = srgs[1];
            BidService.resolverBid(url,bidAddress);
            System.exit(0);
        }
        if (commandLine.hasOption("query-transaction-info")) {
            String[] srgs = commandLine.getOptionValues("query-transaction-info");
            if (srgs.length != 2) {
                Usage();
                System.exit(0);
            }
            String url = srgs[0];
            String hash = srgs[1];
            BidService.queryTransactionInfoByHash(url,hash);
            System.exit(0);
        } if (commandLine.hasOption("get-bid-by-hash")) {
            String[] srgs = commandLine.getOptionValues("get-bid-by-hash");
            if (srgs.length != 2) {
                Usage();
                System.exit(0);
            }
            String url = srgs[0];
            String hash = srgs[1];
            BidService.getBidByHash(url,hash);
            System.exit(0);
        }
    }
}
