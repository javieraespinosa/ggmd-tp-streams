package stormTP.operator;


import java.io.StringReader;
import java.util.Map;
import java.util.logging.Logger;

import java.security.MessageDigest;
import java.util.Base64;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class ConsumeTimeBolt implements IRichBolt {

		private static final long serialVersionUID = 4262369370788456843L;
		private OutputCollector collector;
		private static final Logger logger = Logger.getLogger("ConsumeTimeBolt");
		
			
		/* (non-Javadoc)
		 * @see org.apache.storm.topology.IRichBolt#execute(org.apache.storm.tuple.Tuple)
		 */
		public void execute(Tuple t) {
			
			 logger.info("[ConsumeTime] EXEC");
					
			/* récupération du message */
			String n = t.getValueByField("json").toString();

			/* Exraction des valeurs pour les encrypter */
			JsonReader jr = Json.createReader( new StringReader(n) );
			JsonObject obj = jr.readObject();

			String value = null;
			String couple = null;
			int cpt = 0;
			String indice = null;

			JsonObjectBuilder r = null;
			r = Json.createObjectBuilder();

			for (String key : obj.keySet()) {
				value = obj.get(key).toString();
				couple = key + ":" + encrypter( value) ;
				indice = "" + cpt++;
				r.add( indice, encrypter(couple) );
			}

			JsonObject row = r.build();

			logger.info("[ConsumeTime] " + row.toString());
			collector.emit(t, new Values(row.toString()));
			collector.ack(t);
		}


private String encrypter( String val ){
	String res = "";

	try{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(val.getBytes());
		res =  Base64.getEncoder().encodeToString(hash);
	}catch( Exception e ){
		 e.printStackTrace();
	}
	return res;
}


		/* (non-Javadoc)
		 * @see org.apache.storm.topology.IComponent#declareOutputFields(org.apache.storm.topology.OutputFieldsDeclarer)
		 */
		public void declareOutputFields(OutputFieldsDeclarer arg0) {
			arg0.declare( new Fields("json"));
			
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