package stormTP;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import stormTP.core.Manager;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

  
    
    /**
     * Test pour question 1
     */
    public void testTortoiseFilter()
    {
    	int dossard = 2;

    	Manager mg = new Manager(dossard);

    	String input = "{ \"tortoises\":[ ";
		input += "{\"id\":0,\"nom\":\"turtle1\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":194,\"tour\":2,\"total\":10},";
		input += "{\"id\":1,\"nom\":\"turtle2\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":123,\"tour\":2,\"total\":10},";
		input += "{\"id\":2,\"nom\":\"turtle3\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":67,\"tour\":3,\"total\":10},";
		input += "{\"id\":3,\"nom\":\"turtle4\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":178,\"tour\":2,\"total\":10},";
		input += "{\"id\":4,\"nom\":\"turtle5\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":1,\"tour\":3,\"total\":10},";
		input += "{\"id\":5,\"nom\":\"turtle6\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":164,\"tour\":2,\"total\":10},";
		input += "{\"id\":6,\"nom\":\"turtle7\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":163,\"tour\":2,\"total\":10},";
		input += "{\"id\":7,\"nom\":\"turtle8\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":111,\"tour\":2,\"total\":10},";
		input += "{\"id\":8,\"nom\":\"turtle9\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":159,\"tour\":2,\"total\":10},";
		input += "{\"id\":9,\"nom\":\"turtle10\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":123,\"tour\":2,\"total\":10},";
		input += "] }";
    	
    	String output =  "{\"id\":2,\"nom\":\"turtle3\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":67,\"tour\":3,\"total\":10},";
    	System.out.println("@Test testTortoiseFilter()");
    	
    	System.out.println("input: " +input);
    	
    	String result = mg.filter(dossard, input).getJSON_V1();
 	 
    	
    	System.out.println("output: " + output);
    	System.out.println("result: " + result);
    	System.out.println();
    	
    	 assertEquals(result , output );
    }
    
    
    /**
     * Test1 pour question 2
     */
    public void test1TortoisecomputeRank()
    {
		Manager mg = new Manager();

		String input = "{ \"tortoises\":[ ";
		input += "{\"id\":0,\"nom\":\"turtle1\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":194,\"tour\":2,\"total\":10},";
		input += "{\"id\":1,\"nom\":\"turtle2\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":123,\"tour\":2,\"total\":10},";
		input += "{\"id\":2,\"nom\":\"turtle3\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":67,\"tour\":3,\"total\":10},";
		input += "{\"id\":3,\"nom\":\"turtle4\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":178,\"tour\":2,\"total\":10},";
		input += "{\"id\":4,\"nom\":\"turtle5\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":1,\"tour\":3,\"total\":10},";
		input += "{\"id\":5,\"nom\":\"turtle6\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":164,\"tour\":2,\"total\":10},";
		input += "{\"id\":6,\"nom\":\"turtle7\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":163,\"tour\":2,\"total\":10},";
		input += "{\"id\":7,\"nom\":\"turtle8\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":111,\"tour\":2,\"total\":10},";
		input += "{\"id\":8,\"nom\":\"turtle9\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":159,\"tour\":2,\"total\":10},";
		input += "{\"id\":9,\"nom\":\"turtle10\",\"animal\":\"tortoise\",\"top\":896,\"cellule\":123,\"tour\":2,\"total\":10},";
		input += "] }";

		String output = "{ \"tortoises\":[ ";
		output += "{\"id\":0,\"nom\":\"turtle1\",\"animal\":\"tortoise\",\"top\":896,\"rang\":3,\"total\":10},";
		output += "{\"id\":1,\"nom\":\"turtle2\",\"animal\":\"tortoise\",\"top\":896,\"rang\":8ex,\"total\":10},";
		output += "{\"id\":2,\"nom\":\"turtle3\",\"animal\":\"tortoise\",\"top\":896,\"rang\":1,\"total\":10},";
		output += "{\"id\":3,\"nom\":\"turtle4\",\"animal\":\"tortoise\",\"top\":896,\"rang\":4,\"total\":10},";
		output += "{\"id\":4,\"nom\":\"turtle5\",\"animal\":\"tortoise\",\"top\":896,\"rang\":2,\"total\":10},";
		output += "{\"id\":5,\"nom\":\"turtle6\",\"animal\":\"tortoise\",\"top\":896,\"rang\":5,\"total\":10},";
		output += "{\"id\":6,\"nom\":\"turtle7\",\"animal\":\"tortoise\",\"top\":896,\"rang\":6,\"total\":10},";
		output += "{\"id\":7,\"nom\":\"turtle8\",\"animal\":\"tortoise\",\"top\":896,\"rang\":10,\"total\":10},";
		output += "{\"id\":8,\"nom\":\"turtle9\",\"animal\":\"tortoise\",\"top\":896,\"rang\":7,\"total\":10},";
		output += "{\"id\":9,\"nom\":\"turtle10\",\"animal\":\"tortoise\",\"top\":896,\"rang\":8ex,\"total\":10},";
		output += "] }";

		System.out.println("@Test testComputeRank()");

		String result = output;  // À changer

		System.out.println("output: " + output);
		System.out.println("result: " + result);
		System.out.println();
		assertEquals(result , output );


	}

    
    /**
     * Test1 pour question 3
     */
    public void test1TortoisecomputePoints()
    {
		Manager mg = new Manager();

    	// TODO
      	System.out.println("@Test test1TortoiseComputePoints()");

		  String output = null;

		String result = output;  // À changer

		System.out.println("output: " + output);
		System.out.println("result: " + result);
		System.out.println();
		assertEquals(result , output );
    }
    

    /**
     * Test pour question 4
     */

    public void testTortoiseSpeed()
    {
		Manager mg = new Manager();

		// TODO

		String output = null;

      	System.out.println("@Test test1TortoiseComputeSpeed()");

		String result = output;  // À changer

		System.out.println("output: " + output);
		System.out.println("result: " + result);
		System.out.println();
		assertEquals(result , output );
    }
    
    
    
    /**
     * Test1 pour question 5
     */

    public void test1TortoiseRankEvolution()
    {
		Manager mg = new Manager();

    	//TODO

		String output = null;

      	System.out.println("@Test test1TortoiseRankEvolution()");

		String result = output;  // À changer

		System.out.println("output: " + output);
		System.out.println("result: " + result);
		System.out.println();
		assertEquals(result , output );
    }
    

    
    /**
     * Test4 pour question 5
     */

    public void testTortoiseAverageRank()
    {
		Manager mg = new Manager();

		// TODO

		String output = null;

		System.out.println("@Test testGiveAverageRank()");

		String result = output;  // À changer

		System.out.println("output: " + output);
		System.out.println("result: " + result);
		System.out.println();
		assertEquals(result , output );
    }
    

    
    
    /**
     * Test pour partie 4
     */
    public void testPodium()
    {
		Manager mg = new Manager();

		// TODO

		String output = null;

		System.out.println("@Test testPodium()");

		String result = output;  // À changer

		System.out.println("output: " + output);
		System.out.println("result: " + result);
		System.out.println();
		assertEquals(result , output );


    }
    
    
}
