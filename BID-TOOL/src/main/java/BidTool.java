import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.constant.EncodeType;
import cn.ac.caict.bid.constant.KeyType;
import cn.ac.caict.bid.core.address.Bid;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.model.KeyPairEntity;
import cn.ac.caict.bid.model.Result;
import cn.ac.caict.bid.util.HexFormatUtil;
import org.apache.commons.cli.*;

import java.text.ParseException;
public class BidTool {
    public static void Usage(){
        System.out.printf("Usage: java bid-tool.jar [OPTIONS]\n" +
                "OPTIONS:\n" +
                "  --help                                                                       display this help\n" +
                "  --version                                                                    get bid-sdk version\n" +
                "  --bid-version                                                                get bid version\n" +
                "  --get-bid-and-keypair <keytype>  <encodetype>                                get bid and keypair\n" +
                "  --get-bid-and-keypair-with-chaincode <chaincode>  <keytype>  <encodetype>    get bid and keypair with chaincode\n" +
                "  --get-bid-by-pubkey <public-key>                                             get bid from public key\n" +
                "  --get-pubkey-by-prikey <private-key>                                         get public key from private key\n" +
                "  --sign-data <private-key> <blob-data>                                        sign blob data\n" +
                "  --verify-signed-data <blob data> <signed data> <public key>                  verify signed data\n" +
                "  --check-bid <bid>                                                            check bid format\n"
                );
    }
    public static void main(String[] args) throws ParseException, org.apache.commons.cli.ParseException {
        CommandLineParser commandLineParser = new DefaultParser();
        Options options = new Options();
        options.addOption(null, "help", false, "display this help");
        options.addOption(null, "version", false, "get bid-sdk version");
        options.addOption(null, "bid-version", false, " get bid version");
        options.addOption(null, "get-bid-by-pubkey", true, "get bid from public key");
        options.addOption(null, "get-pubkey-by-prikey", true, "get public key from private key");
        options.addOption(Option.builder(null)
                .longOpt("get-bid-and-keypair")
                .desc("get bid and keypair")
                .hasArg()
                .optionalArg(true)
                .numberOfArgs(3)
                .build());

        options.addOption(Option.builder()
                .longOpt("get-bid-and-keypair-with-chaincode")
                .desc("get-bid-and-keypair-with-chaincode")
                .hasArg()
                .optionalArg(true)
                .numberOfArgs(3)
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
        if (commandLine.hasOption("bid-version")) {
            BidService.getBidVersion();
            System.exit(0);
        }
        if (commandLine.hasOption("get-bid-and-keypair")) {
            String[] srgs = commandLine.getOptionValues("get-bid-and-keypair");
            if(null == srgs || srgs.length == 0){
                BidService.getBidAndKeypair(null);
                System.exit(0);
            }else if(srgs.length ==2){
                String keyTypeStr = srgs[0];
                String encodeTypeStr = srgs[1];
                KeyType keyType = null;
                EncodeType encodeType = null;
                if ("sm2".equals(keyTypeStr)) {
                    keyType = KeyType.SM2;
                } else if ("ed25519".equals(keyTypeStr)) {
                    keyType = KeyType.ED25519;
                } else {
                    System.out.println("The keytype is invalid,keytype need either sm2 or ed25519");
                    Usage();
                    System.exit(0);
                }
                if ("base58".equals(encodeTypeStr)) {
                    encodeType = EncodeType.Base58;
                } else if ("base64".equals(encodeTypeStr)) {
                    encodeType = EncodeType.Base64;
                } else {
                    System.out.println("The encodetype is invalid,encodetype need either base64 or base58");
                    Usage();
                    System.exit(0);
                }
                BidService.getBidAndKeypair(null, keyType, encodeType);
                System.exit(0);
            }else {
                System.out.println("The parameter is illegal,the parameter either null or keytype and encodetype");
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
                BidService.getBidAndKeypair(chainCode);
                System.exit(0);
            }else if(srgs.length == 3){
                String chainCode = srgs[0];
                String keyTypeStr = srgs[1];
                String encodeTypeStr = srgs[2];
                KeyType keyType = null;
                EncodeType encodeType = null;
                if ("sm2".equals(keyTypeStr)) {
                    keyType = KeyType.SM2;
                } else if ("ed25519".equals(keyTypeStr)) {
                    keyType = KeyType.ED25519;
                } else {
                    System.out.println("The keytype is invalid,keytype need either sm2 or ed25519");
                    Usage();
                    System.exit(0);
                }
                if ("base58".equals(encodeTypeStr)) {
                    encodeType = EncodeType.Base58;
                } else if ("base64".equals(encodeTypeStr)) {
                    encodeType = EncodeType.Base64;
                } else {
                    System.out.println("The encodetype is invalid,encodetype need either base64 or base58");
                    Usage();
                    System.exit(0);
                }
                BidService.getBidAndKeypair(chainCode, keyType, encodeType);
                System.exit(0);
            }else {
                System.out.println("The parameter is illegal,the parameter either chaincode or chaincode¡¢keytype and encodetype");
                Usage();
                System.exit(0);
            }
        }
        if (commandLine.hasOption("get-bid-by-pubkey")) {
            String publicKey = commandLine.getOptionValue("get-bid-by-pubkey");
            BidService.getBidByPubKey(publicKey);
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
        } else {
            Usage();
            System.exit(0);
        }
    }
}
