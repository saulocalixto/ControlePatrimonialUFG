# Casos de Uso - InvSCP

O presente documento tem como objetivo documentar os casos de uso do sistema InvSCP, salientando os autores de cada ação e de suas necessidades atendidas pelos casos de uso.

### Sumário

* [Diagrama](#diagrama-de-caso-de-uso)
* [Chefe ou substituto de chefe de departamento](#chefe-de-departamento)
	* Patrimônio
		- [CadPat](#cadpat)
		- [UpdPat](#updpat)
		- [DelPat](#delpat)
		- [VisHis](#vishis)
	* Usuário 
		- [CadUsr](#cadusr)
		- [UpdUsr](#updusr)
		- [DelUsr](#delusr)
	* Localização
		- [CadLoc](#cadloc)
		- [UpdLoc](#updloc)
		- [DelLoc](#delloc)
	* Departamento
		- [ManDep](#mandep)
		- [VincSal](#vincsal)
		- [DesvSal](#desvsal)
	* Movimentação
		- [MovPatEx](#movpatex)
		- [MovPatIn](#movpatin)
		- [AceiMov](#aceimov)
		- [CanMov](#canmov)
		- [Emgutransp](#emgutransp)
	* Serviços
		- [Emrelpat](#emrelpat)
		- [RegOs](#regos)
		- [FechOs](#fechos)
* [Chefe de departamento de bem patrimonial](#chefe-de-departamento-de-bem-patrimonial)
	- [EmInv](#eminv)
	- [BaixPat](#baixpat)
* [Usuário](#usuario)
	- [RegMov](#regmov)
* [Público](#publico)
	- [BusBemPat](#busbempat)
	- [ConDadBemPat](#condadbempat)

### Diagrama de Caso de Uso

![Diagrama de casos de uso](https://github.com/saulocalixto/InvSCP/blob/dev/Design%20Funcional/Casos%20de%20Uso/diagramaCasosDeUso.png?raw=true)

## Chefe de departamento

### *CadPat*

**Título:** Cadastrar um patrimônio

**Descrição resumida:** O sistema deve provê a funcionalidade de cadastrar um novo bem patrimonial que chegou à instituição, podendo informar no ato todas as informações pertinentes a esse novo bem, inclusive localização e departamento ao qual está vinculado. Quando a localização de um bem não é informada, ele é guardado automaticamente na sala de depósito do departamento de patrimônio.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. O usuário deve ter autorização para realizar o cadastro.

**Pós-condição:**
1. Um novo patrimônio foi adicionado à base do sistema, podendo agora ser visualizado, editado, movimentado ou ter sua baixa decretada.

**Sequência típica**
1. Sistema apresenta a tela inicial;
2. O usuário autorizado entra na funcionalidade de cadastro de patrimônio;
3. O sistema devolve a ficha de cadastro;
4. O usuário digita todas as informações solicitadas pelo sistema, inclusive o local onde o patrimônio será guardado;
5. O usuário salva as informações;
6. O sistema apresenta a ficha do bem que acabou de ser cadastrado.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|O local indicado para salvar o patrimônio não existe.|O sistema lançará uma exceção informando que o local de lotação não existe no sistema.|3|
|4|O usuário não informa o local onde o bem será guardado.|O sistema avisa que o bem será guardado na sala de depósito do departamento de patrimônio.|5|
|5|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|1|

### *UpdPat* - Atualizar um bem

**Título:** Atualizar um patrimônio

**Descrição resumida:** O sistema deve provê a funcionalidade de atualização de um bem patrimonial previamente cadastrado observando as restrições:
* O número do patrimônio não pode ser atualizado;
* O grupo de material do patrimônio não pode ser atualizado;

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. O patrimônio deve estar previamente cadastrado;
2. O item atualizado não pode estar dentro do grupo de restrições;

**Pós-condição:**
1. Um patrimônio previamente cadastrado tem seus dados atualizados.

**Sequência típica**
1. O usuário autorizado localiza um patrimônio;
2. O sistema devolve a ficha do bem procurado;
3. O usuário entra na funcionalidade de atualização;
4. O sistema abre a tela de edição do patrimônio;
5. Usuário atualiza os dados;
6. Usuário confirma a operação;
7. Sistema apresenta a ficha do item com os dados atualizados;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|O bem procurado não está cadastrado no sistema.|O sistema lançará uma exceção informando que o bem não está cadastrado.|1|
|5|Usuário tenta atualizar algum dado indevido.|O sistema lança exceção informando que o dado não pode ser atualizado.|4|
|6|Usuário desiste da operação.|O sistema volta para a ficha do bem sem ter os dados atualizados.|2|

### *VisHis*

**Título:** Visualizar histórico de patrimônio

**Descrição resumida:** O sistema deve guardar o histórico de qualquer movimentação feita para um bem patrimonial e o usuário deve poder visualizar esse histórico. Deve conter no histórico:
* Movimentações feitas ao longo de tempo;
* Depreciação ao longo do tempo;
* Os geradas;
* Baixa do bem.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. O bem deve ter mais de um histórico, ou seja ter tido alguma ocorrência de movimentação, Os ou baixa.

**Pós-condição:**
1. A visualização do histórico do bem.

**Sequência típica**
1. O usuário autorizado localiza um patrimônio;
2. O sistema devolve a ficha do bem procurado;
3. O usuário aciona o histórico do bem;
4. O sistema lista todas as datas de histórico do referido bem;
5. Usuário escolhe a data desejada;
6. Sistema informa se houve movimentação, Os ou baixa naquela data;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|O bem procurado não está cadastrado no sistema.|O sistema lançará uma exceção informando que o bem não está cadastrado.|1|
|3|O bem não tem histórico.|O sistema não lista nada.|2|

### *DelPat*

**Título:** Remover um patrimônio

**Descrição resumida:** O sistema deve provê a funcionalidade de excluir um patrimônio da base de dados do sistema de forma permanente, desde que seja seguida determinadas regras:
* O patrimônio que se deseja excluir deve ter sido cadastrado há menos de um mês;
* Não pode ter havido a emissão do inventários contendo o bem;
* Ele não pode ter sido movimentado.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. O patrimônio deve estar previamente cadastrado;
2. O item a ser removido deve ter sido cadastrado há menos de um mês;
3. O item não pode ter constado no inventário emitido;
4. O item não pode ter sido movimentado.

**Pós-condição:**
1. O bem removido da base do sistema.

**Sequência típica**
1. O usuário autorizado localiza um patrimônio;
2. O sistema devolve a ficha do bem procurado;
3. O usuário aciona a funcionalidade de Remoção do patrimônio;
4. O sistema emite um alerta com as consequências do ato;
5. Usuário confirma o ato;
6. Sistema retorna para tela inicial;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|O bem procurado não está cadastrado no sistema.|O sistema lançará uma exceção informando que o bem não está cadastrado.|1|
|5|Usuário não confirma a ação de remoção do bem.|O sistema volta para a ficha de exibição do bem.|2|

### *CadUsr*

**Título:** Cadastrar um usuário

**Descrição resumida:** O sistema deve provê a funcionalidade do chefe de departamento cadastrar um novo usuário que esteja ligado à seu departamento. No ato do cadastro do usuário o chefe deve informar todos os dados obrigatórios exigidos, além do nível de permissão que determinado usuário terá.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. Usuário que será cadastrado deverá ser vinculado ao mesmo departamento do chefe que o está cadastrando.

**Pós-condição:**
1. Um novo usuário é adicionado à base.

**Sequência típica**
1. O usuário autorizado entra na funcionalidade de cadastro de usuário;
2. O sistema devolve a ficha de cadastro de usuário;
3. O usuário digita todas as informações solicitadas pelo sistema, inclusive o nível de permissão que o novo usuário terá no sistema;
4. O usuário salva as informações;
5. O sistema apresenta a ficha do usuário recém cadastrado.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|O departamento do novo usuário diverge do departamento do usuário que está fazendo o cadastro.|O sistema lança exceção avisando que o usuário não tem permissão de cadastrar usuários em departamentos diferente do que ele está vinculado.|2|
|4|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|2|

### *UpdUsr*

**Título:** Atualizar um usuário

**Descrição resumida:** O chefe de departamento pode atualizar os dados dos usuários vinculados ao seu departamento, portanto o sistema deve prover essa funcionalidade a ele.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. O usuário a ser atualizado deve estar vinculado ao departamento do chefe que deseja atualizar o dado;

**Pós-condição:**
1. O usuário tem seus dados atualizados no sistema.

**Sequência típica**
1. O usuário autorizado localiza um usuário;
2. O sistema devolve a ficha do usuário;
3. O usuário entra na funcionalidade de atualização;
4. O sistema abre a tela de edição dos dados do usuário;
5. Usuário atualiza os dados;
6. Usuário confirma a operação;
7. Sistema apresenta a ficha do item com os dados atualizados;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|O usuário não está cadastrado no sistema.|O sistema lançará uma exceção informando que o bem não está cadastrado.|1|
|3|Usuário não tem permissão para atualização.|O sistema lança exceção informando que o usuário só pode atualizar outros usuários vinculados ao seu departamento.|2|
|5|Usuário tenta atualizar algum dado indevido.|O sistema lança exceção informando que o dado não pode ser atualizado.|4|
|6|Usuário desiste da operação.|O sistema volta para a ficha do bem sem ter os dados atualizados.|2|

### *DelUsr*

**Título:** Remover um usuário

**Descrição resumida:** O chefe de departamento pode remover os usuários vinculados ao seu departamento, portanto o sistema deve prover essa funcionalidade a ele.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. O usuário a ser atualizado deve estar vinculado ao departamento do chefe que deseja atualizar o dado;

**Pós-condição:**
1. O usuário é removido do sistema.

**Sequência típica**
1. O usuário autorizado localiza um usuário;
2. O sistema devolve a ficha do usuário;
3. O usuário entra na funcionalidade de apagar;
4. O sistema lança um alerta informando sobre as consequências do ato;
5. Usuário confirma;
6. Sistema retorna para tela inicial.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|O usuário não está cadastrado no sistema.|O sistema lançará uma exceção informando que o bem não está cadastrado.|1|
|3|Usuário não tem permissão para remover o outro usuário.|O sistema lança exceção informando que o usuário só pode atualizar outros usuários vinculados ao seu departamento.|2|
|5|Usuário não confirma a operação.|O sistema retorna para a ficha do usuário.|3|

### *CadLoc*

**Título:** Cadastrar um local

**Descrição resumida:** O usuário deve poder realizar cadastros de locais onde os bens ficarão guardados. Geralmente um bem é guardado em uma sala que é vinculada à um departamento, prédio e endereço. Por tanto ao cadastrar uma localização o usuário deve inserir:
* Sala;
* Prédio;
* Endereço.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. Usuário deve ter permissão para o ato.

**Pós-condição:**
1. Uma nova localização é adicionado à base.

**Sequência típica**
1. O usuário autorizado entra na funcionalidade de cadastro de localização;
2. O sistema devolve a ficha de cadastro da localização;
3. O usuário digita todas as informações solicitadas pelo sistema;
4. O usuário salva as informações;
5. O sistema apresenta a ficha de cadastro da localização;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|O departamento da nova localização diverge do departamento do usuário que está fazendo o cadastro.|O sistema lança exceção avisando que o usuário não tem permissão de cadastrar localização em departamentos diferente do que ele está vinculado.|2|
|4|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|2|

### *UpdLoc*

**Título:** Atualizar um local

**Descrição resumida:** O usuário deve poder realizar a atualização de localizações que sejam vinculadas ao departamento do qual é chefe. Ele deve observar que ao atualizar uma localização todos os bens que forem vinculados a ela também serão atualizados.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. Usuário deve ter permissão para o ato;
2. A localização deve esta cadastrada;

**Pós-condição:**
1. A localização tem seus dados atualizados.

**Sequência típica**
1. O sistema apresenta a tela inicial;
2. O usuário pesquisa a localização que deseja buscar;
3. O sistema devolve a ficha com as informações da localização;
4. O usuário entra na funcionalidade de edição de dados;
5. O sistema apresenta o formulário de edição da localização;
6. O usuário preenche os dados;
7. O usuário confirma a operação;
8. Sistema devolve ficha da localização com os dados atualizados.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|A localização não está cadastrada.|O sistema lança exceção informando que a localização não está cadastrada.|1|
|6|Usuário deixa alguma informação obrigatória sem preencher.|O sistema lança exceção de dado obrigatório não preenchido.|5|
|7|Usuário desiste da operação.|O sistema volta para a ficha da localização.|3|

### *DelLoc*

**Título:** Remover um local

**Descrição resumida:** O usuário deve poder apagar uma localização previamente cadastrada. Contudo é se houver algum patrimônio ou departamento previamente vinculado a essa localização então a exclusão não será permitida.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. Usuário deve ser chefe do departamento do qual a localização está vinculada;
2. Não deve haver nenhum patrimônio ou departamento vinculado à localização.

**Pós-condição:**
1. A localização será excluída da base de dados do sistema.

**Sequência típica**
1. O sistema apresenta a tela inicial;
2. O usuário pesquisa a localização que deseja buscar;
3. O sistema devolve a ficha com as informações da localização;
4. O usuário aciona a funcionalidade de exclusão;
5. O sistema apresenta mensagem informando dos riscos do ato;
6. O usuário confirma a operação;
7. Sistema volta para tela inicial.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|A localização não está cadastrada.|O sistema lança exceção informando que a localização não está cadastrada.|1|
|4|Existe patrimônio ou departamento vinculado à localização.|O sistema informa que não pode ser feita a exclusão por haver patrimônio vinculado à ela.|3|
|6|Usuário não confirma a operação.|O sistema volta para a ficha da localização.|3|

### ManDep

**Título:** Manter departamento.

**Descrição resumida:** O sistema deve criar, atualizar, pesquisar e deletar departamentos.

**Autor:** Chefe do patrimônio, administrador de seção.

**Pré-condição:**
1. <não se aplica>

**Pós-condição:**
1. Um novo departamento é cadastrado

**Sequência típica:** Cadastro de departamento
1. O usuário autorizado entra na funcionalidade de cadastro de departamento;
2. O sistema devolve a ficha de cadastro de departamento;
3. O usuário digita todas as informações solicitadas pelo sistema;
4. O usuário salva as informações;
5. O sistema apresenta a ficha do departamento recém cadastrado;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|O departamento já existe|O sistema lançará uma exceção informando que o departamento já está cadastrado|1|
|4|Usuário não preenche todas as informações necessárias|O sistema exibe uma mensagem avisando que uma informação não foi preenchida|2|
|3|Usuário desiste da operação|O sistema cancela o cadastro do departamento|3|

**Sequência alternativa:** pesquisar departamento
1. O usuário  acessa a funcionalidade de busca de departamentos;
2. O sistema retorna o formulário para busca.
3. O usuário informa ou não critérios de busca.
4. O sistema faz uma busca com os critérios informados.
5. O sistema retorna o conjunto de departamentos condizentes.

**Exceções da Sequência alternativa:** pesquisar departamento

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|Nenhum departamento condiz com os critérios de busca utilizados|O sistema lançará uma exceção informando que não há departamentos cadastrados com estes critérios ou o usuário informou algo errado|1|

**Sequência alternativa:** atualizar departamento
1. O usuário  acessa a funcionalidade de busca de departamentos;
2. O sistema retorna o formulário para busca.
3. O usuário informa ou não critérios de busca.
4. O sistema faz uma busca com os critérios informados.
5. O sistema retorna o conjunto de departamentos condizentes.
6. O usuário seleciona o departamento que deseja editar as informações
7. O sistema retorna a ficha para a edição de informações
8. O usuário modifica as informações que deseja.
9. O usuário salva as alterações.
10. O sistema retorna a ficha do departamento atualizado.

**Exceções da Sequência alternativa:** atualizar departamento

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|Nenhum departamento condiz com os critérios de busca utilizados|O sistema lançará uma exceção informando que não há departamentos cadastrados com estes critérios ou o usuário informou algo errado|1|
|8|O usuário desiste da operação|O sistema cancela o cadastro da baixa|2|

**Sequência alternativa:** deletar departamento

Regras:

O departamento ia ser removido deve ter sido cadastrado há menos de um mês;

Não podem haver salas ligadas ao departamento.

Fluxo:
1. O usuário  acessa a funcionalidade de busca de departamentos;
2. O sistema retorna o formulário para busca;
3. O usuário informa ou não critérios de busca;
4. O sistema faz uma busca com os critérios informados;
5. O sistema retorna o conjunto de departamentos condizentes;
6. O usuário seleciona o departamento que deseja deletar;
7. O departamento é deletado;

**Exceções da Sequência alternativa:** deletar departamento

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|Nenhum departamento condiz com os critérios de busca utilizados|O sistema lançará uma exceção informando que não há departamentos cadastrados com estes critérios ou o usuário informou algo errado|1|
|6|O departamento foi cadastrado a mais de 1 mês|O sistema cancela a operação e informa que o departamento foi cadastrado a mais de 1 mês|2|
|6|O departamento tem sala vinculadas|O sistema cancela a operação e informa que o departamento possui salas vinculadas|3|

### *VincSal*

**Título:** Vincular salas

**Descrição resumida:** O chefe de departamento deve ter a possibilidade de vincular uma sala previamente cadastrada ao departamento de que ele é chefe. Ele só poderá fazer isso se essa sala já não estiver vinculada a um outro departamento.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. Sala não pode esta  vinculada a outro departamento.

**Pós-condição:**
1. Sala é vinculada ao departamento.

**Sequência típica**
1. O sistema apresenta a tela inicial;
2. O usuário pesquisa por uma localização;
3. Sistema devolve a ficha de informações da localização;
4. O usuário entra na funcionalidade de vincular departamento;
5. O sistema lista os departamentos disponíveis de acordo com a permissão do usuário;
6. Usuário confirma operação;
7. Sistema retorna para tela com os dados da localização.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|A localização não está cadastrada no sistema.|O sistema lança exceção informando que a localização não está cadastrada.|1|
|4|Localização já está vinculada a um departamento.|O sistema avisa o usuário que não poderá vincular a sala a um outro departamento.|3|
|6|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|3|

### *DesvSal*

**Título:** Desvincular salas

**Descrição resumida:** O chefe de departamento deve ter a possibilidade de desvincular uma sala previamente cadastrada ao departamento de que ele é chefe. Ele só poderá fazer isso se não houver patrimônio vinculado à sala ou ao departamento.

**Autor:** Chefe e Substituto de chefe de departamento

**Pré-condição:**
1. Sala não pode ter patrimônio vinculado a ela;
2. Departamento não pode ser patrimônio vinculado a ele.

**Pós-condição:**
1. Sala é desvinculada ao departamento.

**Sequência típica**
1. O sistema apresenta a tela inicial;
2. O usuário pesquisa por uma localização;
3. Sistema devolve a ficha de informações da localização;
4. O usuário aciona a funcionalidade de desvincular departamento;
5. O sistema apresenta uma mensagem informando o usuário das consequências do ato;
6. O usuário confirma a operação;
7. Sistema retorna a ficha da localização;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|A localização não está cadastrada no sistema.|O sistema lança exceção informando que a localização não está cadastrada.|1|
|4|Localização não está vinculada ao departamento do usuário.|O sistema avisa o usuário que não poderá desvincular a localização.|3|
|6|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|3|

### *MovPatEx*

**Título:** Movimentação de bem patrimonial externo

**Descrição resumida:** Ao criar a movimentação ela não se conclui imediatamente, mas fica com o status de Aceite de saída, o que significa que ela está aguardando autorização de origem. Como esta movimentação está sendo feita pelo Administrador de seção, a movimentação vai para o estado Aceite de entrada automaticamente, pois é ele mesmo seria responsável por dar o Aceite de saída.

**Autor:** Administrador de seção.

**Pré-condição:**
1. O usuário deve ter autorização para movimentar o bem;
2. O bem não pode estar com o status de baixa.

**Pós-condição:**
1. A movimentação em estado de Aceite de entrada no destino.

**Sequência típica**
1. O usuário localiza o bem no sistema;
2. O sistema apresenta a ficha do bem;
3. Usuário aciona a funcionalidade de movimentação;
4. Sistema apresenta tela de registro de movimentação;
5. Usuário preenche informações relativas à movimentação;
6. Usuário salva movimentação;
7. O sistema cria a movimentação e registra o aceite de saída automaticamente.
8. O sistema apresenta para o usuário a ficha de bem com informação pertinente à movimentação recém cadastrada.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Bem pesquisado não foi localizado|O sistema informará que o bem não foi encontrado|1|
|5|Usuário deixa de informar uma das informações obrigatórias|O sistema lança uma mensagem informando que o dado obrigatório não foi informado|4|
|6|Usuário desiste da operação|O sistema cancela a criação da movimentação|2|


### *MovPatIn*

**Título:** Movimentação de bem patrimonial interno

**Descrição resumida:** Movimentações entre salas do mesmo departamento não precisam do aceite do chefe do departamento, sendo marcadas imediatamente como *Finalizadas*.

**Autor:** Chefe da seção, funcionário da seção

**Pré-condição:**
1. O usuário deve ter autorização para movimentar o bem;
2. O bem não pode estar com o status de baixa;
3. A movimentação deve ser entre salas do mesmo departamento.

**Pós-condição:**
1. O item movimentado fica com o status de *Finalizado* imediatamente;
2. O local onde o item está guardado é atualizado no sistema.

**Sequência típica**
1. O usuário localiza o bem no sistema;
2. O sistema apresenta a ficha do bem;
3. Usuário aciona a funcionalidade de movimentação;
4. Sistema apresenta tela de registro de movimentação;
5. Usuário preenche informações relativas à movimentação;
6. Usuário salva movimentação;
7. Sistema apresenta ficha de bem com informação pertinente à movimentação recém cadastrada.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Bem pesquisado não foi localizado|O sistema informará que o bem não foi encontrado.|1|
|3|O usuário não tem permissão para movimentar o bem.|O sistema informa ao usuário que ele não tem permissão para realizar a ação.|2|
|5|Usuário deixa de informar uma das informações obrigatórias.|O sistema lança uma mensagem informando que o dado obrigatório não foi informado.|4|
|5|Usuário informa uma localização fora do departamento no qual o bem está lotado.|O sistema cadastra a movimentação mas com o status de *Aceite de Saída*.|7|
|6|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|2|

### *AceiMov*

**Título:** Aceite de entrada de bem movimentado

**Descrição resumida:** O chefe do departamento para onde o bem foi movimentado deve autorizar o recebimento do bem. Só quando essa ação for feita é que o local de lotação do bem é atualizado e o status da movimentação fica como *Finalizada*.

**Autor:** Chefe de departamento

**Pré-condição:**
1. O bem deve ter sido movimentado para um departamento diferente da origem.

**Pós-condição:**
1. O item movimentado fica com o status de *Finalizada*;
2. O local onde o item é lotado é atualizado no sistema.

**Sequência típica**
1. O chefe de departamento entra na funcionalidade de *Aceite de entrada*;
2. O sistema listará todas as movimentações que se destinam ao seu departamento e estão aguardando o aval para concluir a operação;
3. O usuário abre a movimentação que deseja avaliar;
4. O sistema apresenta os dados do bem movimentado;
5. Usuário aceita a movimentação do bem.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|Não ter nenhuma movimentação para dar aceite.|O sistema não irá listar nada.|1|

### *CanMov*

**Título:** Cancelamento de movimentação

**Descrição resumida:** A movimentação pode ser recusada pelo chefe de departamento. Tanto movimentação de entrada, como movimentação de saída. Nesses casos a movimentação fica com o status de *Cancelada*.

**Autor:** Chefe da seção

**Pré-condição:**
1. Deve existir uma movimentação com status de *Aceite de saída* ou *Aceite de entrada* para o departamento do chefe logado no sistema

**Pós-condição:**
1. O bem não tem seu local atualizado;
2. A movimentação fica com o status de *Cancelada*.

**Sequência típica**
1. O chefe de departamento entra na funcionalidade de *Aceite de entrada* ou *Aceite de Saída*;
2. O sistema listará todas as movimentações que se destinam ao seu departamento e estão aguardando o aval para concluir a operação;
3. O usuário abre a movimentação que deseja avaliar;
4. O sistema apresenta os dados do bem movimentado;
5. Usuário recusa a movimentação do bem.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|Não ter nenhuma movimentação para dar aceite.|O sistema não irá listar nada.|1|

### *EmGuTransp*

**Título:** Emissão de guia para autorização de transporte

**Descrição resumida:** Quando uma movimentação é feita entre municípios diferentes o chefe de departamento deve emitir um guia para autorização de transporte do bem. Nesse guia deve conter:
* Cidade de origem;
* Cidade de destino;
* Informações do bem movimentado.

**Autor:** Chefe de departamento

**Pré-condição:**
1. Deve haver uma movimentação cujo destino seja em cidade diferente da origem.

**Pós-condição:**
1. É emitido a guia de autorização.

**Sequência típica**
1. O chefe de departamento entra na funcionalidade de *Emissão de guia de transporte*;
2. O sistema apresenta a tela com uma lista de movimentações geradas;
3. Usuário deve informar qual a movimentação que originará a guia;
4. Será apresentada a guia com as informações da movimentação;
5. Usuário confirma as informações;
6. Sistema apresenta a guia pronta para ser impressa.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Não ter nenhuma movimentação emissão da guia.|O sistema não irá listar nada.|2|
|3|Usuário informa uma movimentação que não tem origem e destino em cidades diferentes.|O sistema informará que a movimentação não exige guia de movimentação.|2|

### *EmRelPat*

**Título:** Emissão de relatório de bens patrimoniais

**Descrição resumida:** O sistema deve prover a funcionalidade de emissão de relatórios dos bens patrimoniais vinculados a um determinado departamento. O relatório deve conter as seguintes regras:
* Bens devem ser agrupados por sala;
* Bens devem ser ordenados por grupo de material e número de tombamento;

**Autor:** Chefe de departamento

**Pré-condição:**
1. Deve haver bens vinculados ao departamento.

**Pós-condição:**
1. É emitido relatório com os bens patrimoniais do departamento.

**Sequência típica**
1. O chefe de departamento entra na funcionalidade de *Emissão de relatório patrimonial*;
2. O sistema apresenta a tela o relatório de todos os bens vinculados ao departamento do qual o usuário é chefe;
3. Usuário imprime o relatório.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Não há nenhum bem vinculado ao departamento.|O sistema informa que não há insumos para o relatório.|1|

### *RegOs*

**Título:** Registro de ordem de serviço

**Descrição resumida:** O sistema deve prover a funcionalidade de registro de ordem de serviço quando um bem patrimonial precisa ser consertado. O status do da ordem de serviço fica *Em conserto*.

**Autor:** Chefe de departamento

**Pré-condição:**
1. O bem patrimonial precisa estar cadastrado.

**Pós-condição:**
1. Ordem de serviço é registrada no sistema;
2. A ordem de serviço fica com o status de *Em conserto*.

**Sequência típica**
1. Sistema apresenta tela inicial;
2. O usuário entra na funcionalidade registrar Os;
3. O sistema apresenta formulário com para preenchimento de Os.
4. Usuário informa todas as informações pertinentes, inclusive qual bem que estará indo para conserto;
5. Usuário confirma operação;
6. Sistema informa ao usuário que Os foi registrada com sucesso;
7. A Os fica com o status de *Em conserto*.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|4|Usuário deixa de preencher algum dado obrigatório.|O sistema apresenta exceção informando que dado obrigatório não foi informado.|3|
|5|Usuário desiste da operação.|O sistema volta para a tela inicial.|1|

### *FechOs*

**Título:** Fechar ordem de serviço

**Descrição resumida:** Quando um bem patrimonial sai para conserto é aberto uma Os. Quando esse bem retorna o sistema deve prover a funcionalidade de fechar a Os aberta informando a data de retorno do bem e o valor do serviço. Nesse estado a Os fica com status de *Concluída*.

**Autor:** Chefe de departamento

**Pré-condição:**
1. A Os precisa estar com o status de *Em conserto*.

**Pós-condição:**
1. Ordem de serviço fica com o status de *Concluída*.

**Sequência típica**
1. Sistema apresenta tela inicial;
2. O usuário localiza a Os que deseja finalizar;
3. O sistema apresenta a ficha da Os cadastrada;
4. Usuário entra na funcionalidade de fechar Os;
5. Sistema apresenta formulário para fechamento da Os com data de retorno do bem e valor total do serviço;
6. Usuário preenche os dados;
7. Usuário confirma a operação;
8. Sistema apresenta ficha de cadastro da Os com o status de *Concluída*.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Os não está cadastrada.|O sistema informa que Os não foi encontrada.|1|
|4|Os não está com o status de *Em conserto*.|Sistema informa que Os não encontra-se aberta.|3|
|6|Usuário deixa de informar algum dado.|O sistema informa que dado obrigatório não foi preenchido.|5|
|7|Usuário desiste da operação.|O sistema volta para a tela inicial.|3|

## Chefe de departamento de bem patrimonial

### *EmInv*

**Título:** Emitir inventário

**Descrição resumida:** O chefe do departamento de patrimônio precisa emitir o inventário que é na verdade a relação de bens da instituição. No inventário o sistema deverá permitir que o usuário acompanhe a evolução de bem no momento de publicar o balanço patrimonial, além de acompanhamento de depreciação para poder fazer projeções de compras no futuro.

**Autor:** Chefe do departamento de patrimônio

**Pré-condição:**
1. Deve haver bens cadastrado no sistema.

**Pós-condição:**
1. Um documento contendo as informações de todos os bens da instituição;

**Sequência típica**
1. Sistema apresenta tela inicial;
2. Usuário entra da funcionalidade de emissão de inventário;
3. Sistema apresenta documento contendo todas as informações de bens patrimoniais cadastrados no sistema;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Não há bens patrimoniais cadastrados no sistema.|Sistema informa que não há bens para constar no inventário.|1|

### *BaixPat*

**Título:** Dar baixa em um patrimônio

**Descrição resumida:** Quando o bem patrimonial torna-se desnecessário para a instituição é necessário dar baixa ao mesmo.
Um bem pode ser baixado pelos seguintes motivos:
* Por inservível;
* Por quebra, desgaste ou avaria;
* Por venda em exercícios anteriores;
* Por extravio;
* Por venda direta o leilão;
* Por furto/roubo.

**Autor:** Chefe do departamento de patrimônio

**Pré-condição:**
1. O usuário deve ter autorização para realizar a baixa do bem.

**Pós-condição:**
1. No registro do bem constará a informação da baixa do bem, informando data, autor e motivo;
2. Bem fica impossibilitado de fazer parte do inventário.

**Sequência típica**
1. O usuário localiza o bem no sistema;
2. O sistema apresenta a ficha de registro do bem;
3. Usuário aciona a funcionalidade de baixa;
4. Sistema abre tela de cadastro de baixa;
5. Usuário informa todos as informações exigidas pelo sistema;
6. Usuário salva o cadastro de baixa;
7. Sistema apresenta ficha do bem com o status de *Baixa*.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|O usuário não consegue localizar o bem com as informações de pesquisa.|O sistema informará que o bem não foi encontrado.|1|
|3|O usuário não tem permissão para movimentar o bem.|O sistema informa ao usuário que ele não tem permissão para realizar a ação.|2|
|4|Usuário deixa de informar uma das informações obrigatórias.|O sistema lança uma mensagem informando que o dado obrigatório não foi informado.|4|
|5|Usuário desiste da operação.|O sistema cancela o cadastro da baixa.|1|

## Usuário

### RegMov 

**Título:** Registrar uma movimentação

**Descrição resumida:** Ao criar a movimentação ela não se conclui imediatamente, mas fica com o status de Aceite de saída, o que significa que ela está aguardando autorização de origem.

**Autor:** Usuario (funcionários de seção).

**Pré-condição:**
1. O usuário deve ter autorização para movimentar o bem;
2. O bem não pode estar com o status de baixa.

**Pós-condição:**
1. A movimentação com o estado de Aceite de saída;

**Sequência típica**
1. O usuário localiza o bem no sistema;
2. O sistema apresenta a ficha do bem;
3. Usuário aciona a funcionalidade de movimentação;
4. Sistema apresenta tela de registro de movimentação;
5. Usuário preenche informações relativas à movimentação;
6. Usuário salva movimentação;
7. Sistema apresenta ficha de bem com informação pertinente à movimentação recém cadastrada;

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|1|Bem pesquisado não foi localizado|O sistema informará que o bem não foi encontrado|1|
|3|O usuário não tem permissão para movimentar o bem|O sistema informa ao usuário que ele não tem permissão para realizar a ação|2|
|5|Usuário deixa de informar uma das informações obrigatórias|O sistema lança uma mensagem informando que o dado obrigatório não foi informado|3|
|5|Usuário desiste da operação|O sistema cancela o registro da movimentação|4|

## Público

### *BusBemPat*

**Título:** Buscar bem patrimonial.

**Descrição resumida:** O sistema deve permitir que qualquer pessoa faça uma busca sobre os bens patrimoniais da organização, usando número de tombamento, denominação ou marca como critério de busca(filtro) e retornar um conjunto de bens que condizem com a busca.

**Autor:** Qualquer pessoa interessada (não é necessário login)

**Pré-condição:**

1. <não se aplica>.

**Pós-condição:**
1. Um conjunto de bens patrimoniais resultantes da busca ordenados por denominação e data de aquisição.

**Sequência típica:**
1. A pessoa interessada informa um conjunto de critérios de busca (número de tombamento, denominação ou marca).
2. O sistema faz a busca utilizando os filtros informados.
3. O sistema retorna à pessoa o conjunto (ordenado) de bens patrimoniais que foram identificados.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Nenhum bem condiz aos critérios de busca utilizados|O sistema lançará uma exceção informando que não há bens cadastrados com estes critérios ou o usuário informou algo errado|1|

**Sequência alternativa: Conjunto de todos os bens**

A pessoa não informa nenhum critério de busca.
O sistema emite uma mensagem de confirmação avisando que nenhum critério foi definido então será retornado TODOS os bens patrimoniais.
A pessoa aceita.
O sistema retorna todos os bens.
Sequência alternativa: Pessoa esqueceu de informar os critérios.
A pessoa não informa nenhum critério de busca.
O sistema emite uma mensagem de confirmação avisando que nenhum critério foi definido então será retornado TODOS os bens patrimoniais.
A pessoa recusa.
Retorna para a sequência típica

### ConDadBemPat

**Título:** Consultar dados de um bem patrimonial.

**Descrição resumida:** O sistema deve que qualquer pessoa interessada veja os dados completos de um bem patrimonial.

**Autor:** Qualquer pessoa interessada (não é necessário login)

**Pré-condição:**
1. <não se aplica>.

**Pós-condição:**
1. Os dados completos (o número do tombamento, denominação, data de aquisição, número da nota fiscal, grupo de material, vida útil, especificação, garantia, marca, valor de compra, situação e localização) de um bem patrimonial.

**Sequência típica**
1. A pessoa interessada informa um conjunto de critérios de busca (número de tombamento, denominação ou marca).
2. O sistema faz a busca utilizando os filtros informados.
3. O sistema retorna à pessoa o conjunto (ordenado) de bens patrimoniais que foram identificados.
4. A pessoa seleciona o bem que tem interesse em saber os dados.
5. O sistema retorna os dados completos daquele bem que foi selecionado.

**Exceções da Sequência Típica**

| Passo | Condição | Tratamento da Exceção | Retorno |
|-------|----------|-----------------------|---------|
|2|Nenhum bem condiz aos critérios de busca utilizados|O sistema lançará uma exceção informando que não há bens cadastrados com estes critérios ou o usuário informou algo errado|1|



