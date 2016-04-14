/**
 *  This file is part of FaMaTS.
 *
 *  FaMaTS is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  FaMaTS is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with FaMaTS.  If not, see <http://www.gnu.org/licenses/>.
 */
package co.icesi.i2t.Choco3Reasoner.tests.simple.questions;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import co.icesi.i2t.Choco3Reasoner.simple.questions.Choco3OneProductQuestion;
import co.icesi.i2t.FAMA.TestSuite2.TestLoader;
import co.icesi.i2t.FAMA.TestSuite2.reasoners.AbstractReasonerQuestionTestCase;
import es.us.isa.FAMA.models.FAMAfeatureModel.Feature;
import es.us.isa.FAMA.models.featureModel.Product;

/**
 * Test case for the One Product question in the Choco 3 Reasoner.
 * 
 * @author Andrés Paz, I2T Research Group, Icesi University, Cali - Colombia
 * @version 1.0, October 2014
 */
public class Choco3OneProductQuestionTestCase extends
		AbstractReasonerQuestionTestCase {

	/**
	 * Test configuration file path
	 */
	private static final String TEST_CONFIG_FILE = "test-resources/Choco3TestConfig.xml";

	/**
	 * Question name
	 */
	private static final String QUESTION = "OneProduct";
	
	public Choco3OneProductQuestionTestCase(String variabilityModelPath,
			String input, String expectedOutput) {
		super(variabilityModelPath, input, expectedOutput);
	}

	/**
	 * Loads the tests for the number of products question. Tests are specified
	 * in an XML file with the information of the variability models to test and
	 * the questions to be asked with their expected output.
	 * 
	 * @return A collection of 3-tuples (feature model, input, expected output).
	 * @throws FileNotFoundException
	 *             If the test configuration file is not found.
	 * @throws Exception
	 *             If any other errors occur.
	 */
	@Parameters
	public static Collection<?> loadTests() throws FileNotFoundException,
			Exception {
		return Arrays.asList(TestLoader.loadQuestionTests(TEST_CONFIG_FILE, QUESTION));
	}
	
	/**
	 * Test method for {@link co.icesi.i2t.Choco3Reasoner.simple.questions.Choco3OneProductQuestion#getProduct()}.
	 */
	@Test
	public void testGetProduct() {
		System.out.println("\n[TEST] One Product");
		
		// Load the variability model that will be evaluated during the test.
		variabilityModel = questionTrader.openFile(variabilityModelPath);
		questionTrader.setVariabilityModel(variabilityModel);
		System.out.println("For model: \"" + variabilityModelPath + "\"");
		
		// Create the question instance to be tested.
		Choco3OneProductQuestion choco3OneProductQuestion = (Choco3OneProductQuestion) questionTrader.createQuestion(QUESTION);
		
		if (choco3OneProductQuestion != null) {
			try {
				// Ask the question.
				questionTrader.ask(choco3OneProductQuestion);
				
				// Retrieve the result.
				Product output = (Product) choco3OneProductQuestion.getProduct();
				
				if (!expectedOutput.equals("")) {
					LinkedList<Product> expectedProducts = new LinkedList<Product>();
					String[] expectedOutputs = expectedOutput.split(";");
					for (int i = 0; i < expectedOutputs.length; i++) {
						String expectedProduct = expectedOutputs[i];
						String[] expectedFeatures = expectedProduct.split(":");
						Product product = new Product();
						for (int j = 0; j < expectedFeatures.length; j++) {
							String expectedFeature = expectedFeatures[j];
							Feature feature = new Feature(expectedFeature);
							product.addFeature(feature);
						}
						expectedProducts.add(product);
					}
					
					System.out.println("Obtained product: " + output);
					
					// Compare the result against an expected output value.
					if (expectedProducts.contains(output)) {
						System.out.println("Obtained product is valid.");
						System.out.println("[INFO] Test case passed");
					} else {
						System.out.println("Obtained product is not valid.");
						System.out.println("[INFO] Test case failed");
					}
				} else {
					System.out.println("[INFO] No expected output for test case.");
					System.out.println("Obtained product: " + output);
					System.out.println("[INFO] Test case ignored");
				}
			} catch (AssertionError e) {
				System.out.println("[INFO] Test case failed. Cause: " + e.getMessage());
				throw e;
			} catch (Exception e) {
				System.out.println("[INFO] Test case failed. Cause: " + e.getMessage());
				throw e;
			}
		} else {
			fail("Current reasoner does not accept this operation.");
			System.out.println("[INFO] Current reasoner does not accept this operation.");
		}
	}

}
