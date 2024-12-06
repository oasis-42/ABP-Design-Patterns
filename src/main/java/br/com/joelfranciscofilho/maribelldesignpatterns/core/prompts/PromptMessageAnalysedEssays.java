package br.com.joelfranciscofilho.maribelldesignpatterns.core.prompts;

public class PromptMessageAnalysedEssays {
    public static String get() {
        return """ 
        INSTRUÇÕES DE AVALIAÇÃO Avalie para todas as competências do ENEM:\s
        1. Domínio da escrita formal da língua portuguesa\s
        2. Compreender o tema e não fugir do que é proposto\s
        3. Selecionar, relacionar, organizar e interpretar informações, fatos, opiniões e argumentos em defesa de um ponto de vista.\s
        4. Conhecimento dos mecanismos linguísticos necessários para a construção da argumentação.\s
        5. Respeito aos direitos humanos.\s
        Assim, apresente as considerações para cada uma das 5 competências nas seguintes propriedades json,\s
        todas as propriedades json com letras minúsculas, sem acento e seguindo camel case, o nome das propriedades em inglês mas o conteúdo em pt-br,\s
        seguindo o seguinte formato deverá ser retornando  com um objeto do json para cada competência:\s
        \s
        {
            "essayAnalysis": [
                {
                    "analyzedSkill": "1"\s
                    "grade": 120,
                    "feedback": "",
                    "successes": [
                        {
                            "excerpt": "",
                            "reason": ""
                        }                       \s
                    ],
                    "errors": [
                        {
                            "excerpt": "",
                            "reason": ""
                            "howToCorrect": ""
                        }\s
                    ]
                },
            ]
        }\s
       \s
        Legenda:
        analyzedSkill: o número de um a 1 referente a competência do ENEM.
        grade: o número de 0 a 200 para a nota de competência analisada.
        feedback: Parecer geral sobre a competência analisada.
        successes: [
            {
                excerpt: trecho do texto que apresenta os acertos em relação a competência
                reason: explicação do motivo que o trecho está errado
            }\s
        ],\s
        errors: [
            {
                excerpt: trecho do texto que apresenta os erros em relação a competência
                reason: explicação do motivo que o trecho está errado
                howToCorrect: como corrigir o trecho que está errado
            }\s
        ]\s
       \s
        Avalie utilizando as INSTRUÇÕES DE AVALIAÇÃO para a seguinte redação:
        """;
    }
}
