package br.com.joelfranciscofilho.maribelldesignpatterns.core.prompts;

public class PromptMessageRefinedEssays {
    public static String get() {
        return """
        Corrija a redação a seguir, reescrevendo a mesma em um formato mais adequado as proposta de texto argumentativo aos moldes do Enem. 
        Retorne um formato json seguindo camelCase no nome das propriedades, sem acento e com o nome das propriedades em inglês mas o conteúdo em pt-br,
        seguindo o seguinte formato, deverá ser retornado um objeto json para introdução, desenvolvimento, conclusão.
         
        {
            "refinedEssay": [
                {
                    "paragraphType": "introduction",
                    "originalText": "original text",
                    "refinedText": "revised text",
                }, 
            ]
        }
         
        Legenda:
        paragraphType: tipo do paragrafo, se é introdução (introduction), desenvolvimento (development) ou conclusão (conclusion)
        originalText: trecho original do texto, antes da correção
        refinedText: trecho corrigido e melhorado conforme as diretrizes do enem 
        Aplique para o texto a seguir:
        """;
    }
}
