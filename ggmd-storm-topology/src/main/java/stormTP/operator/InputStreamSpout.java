package stormTP.operator;

import java.util.Map;
import java.util.logging.Logger;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author lumineau
 *
 */
public class InputStreamSpout extends BaseRichSpout {

	private static final long serialVersionUID = -299357684149329360L;
	private static Logger logger = Logger.getLogger("InputSpoutLogger");
	private SpoutOutputCollector collector;
	private String host;
	private int port;
	private Socket socket;
	private BufferedReader reader;
	
	/**
	 * 
	 */
	public InputStreamSpout(String host, int port ) {
		this.host = host;
		this.port = port;

	}
	
	
	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#nextTuple()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void nextTuple() {

		try {

			if (reader.ready()) {
				String json = reader.readLine();
				if (json != null) {
					collector.emit(new Values(json));
					logger.info("EMIT OK");
				}
			}

			// Nécessaire pour éviter que Storm bloque la fonction
			Utils.sleep(10);

		} catch (Exception e) {
			e.printStackTrace();
			Utils.sleep(1000);
		}
	}

	
	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#open(java.util.Map, org.apache.storm.task.TopologyContext, org.apache.storm.spout.SpoutOutputCollector)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;

		try {
			socket = new Socket(host, port);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Spout connected to " + host + ":" + port);
		} catch (Exception e) {
			throw new RuntimeException("Cannot connect to socket", e);
		}

	}

	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#close()
	 */
	@Override
	public void close() {
		logger.info("StreamSimSpout " + InputStreamSpout.serialVersionUID + " is being closed.");
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#activate()
	 */
	@Override
	public void activate() {
		logger.info("StreamSimSpout " + InputStreamSpout.serialVersionUID + " is being activated.");
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#deactivate()
	 */
	@Override
	public void deactivate() {
		logger.info("StreamSimSpout " + InputStreamSpout.serialVersionUID + " is being deactivated.");
	}

	

	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#ack(java.lang.Object)
	 */
	@Override
	public void ack(Object msgId) {
		logger.info("*****  ACK  :   " + msgId + " *******");
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.spout.ISpout#fail(java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void fail(Object msgId) {
		logger.info("***** FAIL  :   " + msgId + " *******");
	
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.topology.IComponent#declareOutputFields(org.apache.storm.topology.OutputFieldsDeclarer)
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare( new Fields("json"));
		
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.topology.IComponent#getComponentConfiguration()
	 */
	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
}