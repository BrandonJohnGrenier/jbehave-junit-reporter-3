/**
 *  Copyright 2012 Brandon Grenier
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.moralesce.jbehave;

import java.lang.annotation.Annotation;

import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.junit.runner.Description;

public final class DescriptionGenerator {

	public static Description createDescription(Story story, Class<?> testClass) {
		return createStoryDescription(story, testClass);
	}

	private static Description createStoryDescription(Story story, Class<?> testClass) {
		Description storyDescription = Description.createSuiteDescription(cleanStoryDescription(story.getDescription().toString()), new Annotation[0]);

		int scenarioNumber = 1;
		for (Scenario scenario : story.getScenarios()) {
			storyDescription.addChild(createScenarioDescription(scenario, scenarioNumber, testClass));
			scenarioNumber++;
		}

//		System.out.println(storyDescription.getDisplayName());
//		for (Description scenario : storyDescription.getChildren()) {
//			System.out.println("  " + scenario.getDisplayName());
//			for (Description step : scenario.getChildren()) {
//				System.out.println("    " + step.getDisplayName());
//			}
//		}

		return storyDescription;
	}

	private static String cleanStoryDescription(String description) {
		return description.replace("Description[descriptionAsString=", "");
	}

	private static Description createScenarioDescription(Scenario scenario, Integer scenarioNumber, Class<?> testClass) {
		Description scenarioDescrption = Description.createSuiteDescription("Scenario " + scenarioNumber + ": " + scenario.getTitle(), new Annotation[0]);

		int stepNumber = 1;
		for (String stepDescription : scenario.getSteps()) {
			String context = JUnitStoryRunner.getStoryCounter() + "" + scenarioNumber + "" + stepNumber + " - ";
			scenarioDescrption.addChild(createStepDescription(context, stepDescription, testClass));
			stepNumber++;
		}

		return scenarioDescrption;
	}

	private static Description createStepDescription(String context, String description, Class<?> stepClass) {
		return Description.createTestDescription(stepClass, context + description);
	}

}
