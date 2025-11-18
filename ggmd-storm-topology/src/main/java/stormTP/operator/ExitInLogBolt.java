package stormTP.operator;



import java.util.Map;
import java.util.logging.Logger;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import stormTP.stream.StreamEmiter;


public class ExitInLogBolt implements IRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4262369370788107342L;
	private static Logger logger = Logger.getLogger("ExitInLogBolt");
	private OutputCollector collector;
	
	public ExitInLogBolt () {
		
	}
	
	/* (non-Javadoc)
	 * @see org.apache.storm.topology.IRichBolt#execute(org.apache.storm.tuple.Tuple)
	 */
	public void execute(Tuple t) {
	
		String n = t.getValueByField("json").toString();
		logger.info("[ExitInLOG]" + n);
		collector.ack(t);
		
		return;
		
	}
	

	
	/* (non-Javadoc)
	 * @see org.apache.storm.topology.IComponent#declareOutputFields(org.apache.storm.topology.OutputFieldsDeclarer)
	 */
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		arg0.declare(new Fields("json"));
	}
		

	/* (non-Javadoc)
	 * @see org.apache.storm.topology.IComponent#getComponentConfiguration()
	 */
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.topology.IBasicBolt#cleanup()
	 */
	public void cleanup() {
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.apache.storm.topology.IRichBolt#prepare(java.util.Map, org.apache.storm.task.TopologyContext, org.apache.storm.task.OutputCollector)
	 */
	@SuppressWarnings("rawtypes")
	public void prepare(Map arg0, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}
}