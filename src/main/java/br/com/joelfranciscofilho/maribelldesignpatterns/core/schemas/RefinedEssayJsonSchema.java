package br.com.joelfranciscofilho.maribelldesignpatterns.core.schemas;

public class RefinedEssayJsonSchema {
    public static String get() {
        return """
                {
                  "$schema": "http://json-schema.org/draft-07/schema#",
                  "title": "Generated schema for Root",
                  "type": "object",
                  "properties": {
                    "refinedEssay": {
                      "type": "array",
                      "items": {
                        "type": "object",
                        "properties": {
                          "paragraphType": {
                            "type": "string"
                          },
                          "originalText": {
                            "type": "string"
                          },
                          "refinedText": {
                            "type": "string"
                          }
                        },
                        "required": [
                          "paragraphType",
                          "originalText",
                          "refinedText"
                        ],
                        "additionalProperties": false
                      }
                    }
                  },
                  "required": [
                    "refinedEssay"
                  ],
                  "additionalProperties": false
                }
                """;
    }

}
