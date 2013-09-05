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

import java.util.List;
import java.util.Map;

import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.junit.runner.Description;

public final class DescriptionGenerator {

	private static final String COMMENT_PREFIX = "!--";

	public static Description createDescription(List<Story> stories, Class<?> testClass) {

		Description suite = Description.createSuiteDescription(testClass.getSimpleName());

		int count = 0;
		for (Story story : stories) {
			count++;
			suite.addChild(createStoryDescription(Integer.toString(count), story, testClass));
		}

		return suite;
	}

	private static Description createStoryDescription(String prefix, Story story, Class<?> testClass) {
		if (story.getDescription().asString() == null) {
			return null;
		}

		Description storyDescription = Description.createSuiteDescription(story.getDescription().asString());

		int count = 0;
		for (Scenario scenario : story.getScenarios()) {
			count++;
			storyDescription.addChild(createScenarioDescription(prefix + ":" + count, scenario, testClass));
		}

		return storyDescription;
	}

	private static Description createScenarioDescription(String prefix, Scenario scenario, Class<?> testClass) {

		Description scenarioDescription = Description.createSuiteDescription("Scenario: " + scenario.getTitle());

		int count = 0;

		if (scenario.getExamplesTable().getRows().isEmpty()) {

			for (String stepDescription : scenario.getSteps()) {

				if (!stepDescription.startsWith(COMMENT_PREFIX)) {
					count++;
					scenarioDescription.addChild(Description.createTestDescription(testClass, prefix + ":" + count
							+ " - " + singleLine(stepDescription)));
				}
			}

		} else {

			for (Map<String, String> row : scenario.getExamplesTable().getRows()) {

				for (String stepDescription : scenario.getSteps()) {

					if (!stepDescription.startsWith(COMMENT_PREFIX)) {
						count++;
						scenarioDescription.addChild(Description.createTestDescription(testClass, prefix + ":" + count
								+ " - " + replaceRowValues(singleLine(stepDescription), row)));
					}
				}
			}
		}

		return scenarioDescription;
	}

	private static String singleLine(String stepDescription) {
		return stepDescription.replace("\n", "").replace("\r", "");
	}

	private static String replaceRowValues(String stepDescription, Map<String, String> values) {

		for (Map.Entry<String, String> value : values.entrySet()) {
			stepDescription = stepDescription.replace("<" + value.getKey() + ">", "<" + value.getValue() + ">");
		}
		return stepDescription;
	}
}
