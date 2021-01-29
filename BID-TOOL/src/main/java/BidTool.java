import cn.ac.caict.bid.SDK;
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
                "  --help                                                                       display this help\n"+
                "  --version                                                                    get bid-sdk version\n"+
                "  --bid-version                                                                get bid version\n"+
                "  --get-bid-and-keypair                                                        get bid and keypair\n"+
                "  --get-bid-and-keypair-with-chaincode <get-bid-and-keypair-with-chaincode>    get bid and keypair with chaincode\n"+
                "  --get-bid-by-pubkey <public-key>                                             get bid from public key\n"+
                "  --get-pubkey-by-prikey <private-key>                                         get public key from private key\n"+
                "  --sign-data <private-key> <blob-data>                                        sign blob data\n"+
                "  --verify-signed-data <blob data> <signed data> <public key>                  verify signed data\n"+
                "  --check-bid <bid>                                                            check bid format\n"
                );
    }
    public static void main(String[] args) throws ParseException, org.apache.commons.cli.ParseException {
        CommandLineParser commandLineParser = new DefaultParser();
        Options options = new Options();
        options.addOption(null,"help" ,false, "display this help");
        options.addOption(null, "version", false,"get bid-sdk version");
        options.addOption(null, "bid-version", false," get bid version");
        options.addOption(null, "get-bid-and-keypair",false," get bid and keypair");
        options.addOption(null, "get-bid-and-keypair-with-chaincode",true," get bid and keypair");


        options.addOption(null, "get-bid-by-pubkey", true,"get bid from public key");
        options.addOption(null, "get-pubkey-by-prikey", true,"get public key from private key");

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
                BidService.getBidAndKeypair(null);
                System.exit(0);
            }
            if (commandLine.hasOption("get-bid-and-keypair-with-chaincode")) {
                String chainCode = commandLine.getOptionValue("get-bid-and-keypair-with-chaincode");
                BidService.getBidAndKeypair(chainCode);
                System.exit(0);
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
                if (srgs.length != 2){
                    Usage();
                    System.exit(0);
                }
                String privateKey = srgs[0];
                String msg = srgs[1];
                BidService.signedBlob(privateKey,msg);
                System.exit(0);
            }
            if (commandLine.hasOption("verify-signed-data")) {
                String[] srgs = commandLine.getOptionValues("verify-signed-data");
                if (srgs.length != 3){
                    Usage();
                    System.exit(0);
                }
                String msg = srgs[0];
                String sig = srgs[1];
                String publicKey = srgs[2];

                BidService.verifysing(publicKey,msg, sig);
                System.exit(0);
            }
            if (commandLine.hasOption("check-bid")) {
                String bid = commandLine.getOptionValue("check-bid");
                BidService.checkBid(bid);
                System.exit(0);
            }
            else{
                Usage();
                System.exit(0);
            }
    }
}
