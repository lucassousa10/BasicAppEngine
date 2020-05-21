# PROPÓSITO

Minha ideia com este projeto é estudar os mais diversos consceitos do desenvolvimento de uma engine do zero.

De uma forma razoável, pretendo construir alguns outros projetos utilizando essa engine, a fim de ter uma perspectiva prática da usabilidade desses códigos.

Utilizo aqui a linguagem Java já que ela permite uma integração razoável com a memória do dispositivo. De fato não é uma comunicação direta, mas a JVM oferece recursos que fazem do Java uma escolha melhor para esse tipo de projeto que linguagens de níveis mais altos, como por exemplo o recurso de BufferStrategy (link: https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferStrategy.html).

# Desafios
Programar uma engine exige basicamente uma boa habilidade em organização e, claro, em otimização.

Em termos de otmização, as estratégias giram em torno da redução de ações repetitivas, uma vez que a base de toda a engine é um grande loop que ficará ativo até que o usuário permita.

Se vê-se que uma operação irá se repetir avalia-se a possibilidade de guardar seu resultado na memória, em alguma estrutura que poderá ser acessada posteriormente para apenas prover os dados em questão. Operações que não têm a possilidade de terem seus resultados gravados em variáveis da memória em último caso podem ser repetidas, entretando é ideal que sempre seja buscada uma forma de substituir tais processos por outros mais adequados.

Além disso, otimização requer que não exageremos no uso de recursos da máquina do usuário. Guardar dados de operações na memória desenfreadamente pode nos fazer consumir espaço de mais. Além do que irá exisgir mais processamento já que precisaremos obter tais valores da memória. 