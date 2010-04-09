// Generated code
import palinTheorem.*;

import com.facebook.thrift.TException;
import com.facebook.thrift.transport.TTransport;
import com.facebook.thrift.transport.TSocket;
import com.facebook.thrift.protocol.TBinaryProtocol;
import com.facebook.thrift.protocol.TProtocol;

public class JavaClient {
  public static void main(String [] args) {
    try {

      TTransport transport = new TSocket("localhost", 9090);
      TProtocol protocol = new TBinaryProtocol(transport);
      PalinTheorem.Client client = new PalinTheorem.Client(protocol);

      transport.open();

      String quote = client.genQuote();

      SharedStruct log = client.getStruct(1);

      transport.close();

    } catch (TException x) {
      x.printStackTrace();
    }

  }

}
