# Conversão de JSON para uma classe em Java

### Este projeto está estruturado da seguinte forma:
###

```ConvertObjectFromJSON``` - Esta interface é responsável por definir os métodos
que as classes responsáveis por converter um atributo do JSON para o respectivo atributo
de uma classe deverão possuir.

```ConvertToTypeField``` - Está interface é responsável por definir a implementação
que será utilizada na converssão de objetos que herdem das interfaces ```Temporal```
e ```Collection```, por exemplo, onde há variações e implementações diferentes 
(ex: LocalDate, LocalTime / List, Set).

```PredicateFieldFromJSON``` - Está interface define métodos que são responsáveis
pelo ```Predicate<Field>``` que atenda a condição para então realizar a conversão do atributo
no JSON e também por especificar a classe que decidirá qual implementação irá escolher
para converter um valor do JSON para o respectivo Field da classe.

A classe ```ConvertJSONToObject``` contém o método estático responsável por chamar
as outras rotinas que são responsáveis por converter e setar no field da classe
passada como argumento o valor gerado após a extração de um atributo do JSON.
