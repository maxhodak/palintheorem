import com.facebook.thrift.server.TServer;
import com.facebook.thrift.server.TSimpleServer;
import com.facebook.thrift.transport.TServerSocket;
import com.facebook.thrift.transport.TServerTransport;

import palinTheorem.*;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class JavaServer {

	protected static MemCachedClient mcc = new MemCachedClient();
  
	static {
		String[] servers =
			{
			  //"8.17.84.43:11211",
			  "70.32.121.118:11211",
			};

		Integer[] weights = {};
		SockIOPool pool = SockIOPool.getInstance();

		pool.setServers( servers );
		pool.setWeights( weights );

		pool.setInitConn( 5 );
		pool.setMinConn( 5 );
		pool.setMaxConn( 250 );
		pool.setMaxIdle( 1000 * 60 * 60 * 6 );

		pool.setMaintSleep( 30 );

		pool.setNagle( false );
		pool.setSocketTO( 3000 );
		pool.setSocketConnectTO( 0 );

		pool.initialize();

		mcc.setCompressEnable( false );
	}
	
	public static class PalinTheoremHandler implements PalinTheorem.Iface {

    public PalinTheoremHandler() {
    }

    public String genQuote() {
      Palin monkey = new Palin(mcc);
      return monkey.RunPalin();
    }

  }

  public static void main(String [] args) {
    try {
      PalinTheoremHandler handler = new PalinTheoremHandler();
      PalinTheorem.Processor processor = new PalinTheorem.Processor(handler);
      TServerTransport serverTransport = new TServerSocket(9090);
      TServer server = new TSimpleServer(processor, serverTransport);

      server.serve();

    } catch (Exception x) {
      x.printStackTrace();
    }
  }

}
