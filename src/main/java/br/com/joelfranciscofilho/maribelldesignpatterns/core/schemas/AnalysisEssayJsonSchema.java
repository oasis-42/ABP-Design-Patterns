package br.com.joelfranciscofilho.maribelldesignpatterns.core.schemas;

public class AnalysisEssayJsonSchema {
    public static String get() {
        return """
        {
          "$schema": "http://json-schema.org/draft-07/schema#",
          "title": "Generated schema for Root",
          "type": "object",
          "properties": {
            "essayAnalysis": {
              "type": "array",
              "items": {
                "type": "object",
                "properties": {
                  "analyzedSkill": {
                    "type": "string"
                  },
                  "grade": {
                    "type": "number"
                  },
                  "feedback": {
                    "type": "string"
                  },
                  "successes": {
                    "type": "array",
                    "items": {
                      "type": "object",
                      "properties": {
                        "excerpt": {
                          "type": "string"
                        },
                        "reason": {
                          "type": "string"
                        }
                      },
                      "required": [
                        "excerpt",
                        "reason"
                      ],
                      "additionalProperties": false
                    }
                  },
                  "errors": {
                    "type": "array",
                    "items": {
                      "type": "object",
                      "properties": {
                        "excerpt": {
                          "type": "string"
                        },
                        "reason": {
                          "type": "string"
                        },
                        "howToCorrect": {
                          "type": "string"
                        }
                      },
                      "required": [
                        "excerpt",
                        "reason",
                        "howToCorrect"
                      ],
                      "additionalProperties": false
                    }
                  }
                },
                "required": [
                  "analyzedSkill",
                  "grade",
                  "feedback",
                  "successes",
                  "errors"
                ],
                "additionalProperties": false
              }
            }
          },
          "required": [
            "essayAnalysis"
          ],
          "additionalProperties": false
        }
        """;
    }
}
