# Projeto Avaliativo Substitutivo

## Aplicativo de Controle de Medicamentos

### Descrição

Este projeto tem como objetivo desenvolver um aplicativo mobile para auxiliar usuários, especialmente pessoas idosas, no controle de seus medicamentos. O app permitirá cadastrar medicamentos, definir dosagens e horários de uso, além de marcar aqueles que já foram tomados. Todos os dados serão armazenados localmente, garantindo disponibilidade mesmo com o app fechado.

### Funcionalidades Obrigatórias

1. **Cadastro de Medicamentos**

   * Nome do medicamento
   * Dosagem (ex: 500mg)
   * Horário de uso (um ou mais horários por dia)

2. **Lista de Medicamentos**

   * Exibição de todos os medicamentos cadastrados
   * Informações: nome, dosagem e horários
   * Ações: editar e excluir

3. **Marcar como "Tomado"**

   * Permitir que o usuário marque o medicamento como já tomado no horário indicado

4. **Persistência de Dados Localmente**

   * Salvar todas as informações no dispositivo para acesso offline

5. **Interface Intuitiva**

   * Layout simples, fontes legíveis e botões de fácil alcance, adequado para público idoso

### Sugestão de Estrutura de Telas

1. **Tela Inicial (Lista de Medicamentos)**

   * Lista simples com o nome, dosagem e próximo horário
   * Botão flutuante para adicionar novo medicamento

2. **Tela de Cadastro**

   * Formulário com campos para nome, dosagem e horário(s)
   * Botões “Salvar” e “Cancelar”

3. **Tela de Edição** (mesma do cadastro, pré-carregada)

   * Alteração de dados já cadastrados

4. **Marcar como "Tomado"**

   * Checkbox ou botão ao lado de cada item na lista

### Tecnologias Compatíveis

* React Native (Expo)
* Flutter
* Android Studio (Java ou Kotlin)
* Outra ferramenta previamente aprovada pelo professor

> **Escolha a tecnologia de sua preferência e configure o ambiente de desenvolvimento conforme a documentação oficial.**

### Instalação e Execução

1. **Clone o repositório**

   ```bash
   git clone <link-do-seu-repositório>
   ```

2. **Entre na pasta do projeto**

   ```bash
   cd <nome-do-projeto>
   ```

3. **Instale as dependências**

   * **React Native (Expo)**: `npm install` ou `yarn install`
   * **Flutter**: `flutter pub get`
   * **Android Studio/Java/Kotlin**: importe o projeto diretamente no Studio

4. **Execute o aplicativo**

   * **Expo**: `expo start`
   * **Flutter**: `flutter run`
   * **Android Studio**: clique em Run

### Como Usar

Tela de Cadastro (tela inicial)

Ao abrir o app, já aparece o formulário de cadastro de medicamento

Campos: nome, dosagem e horário(s)

Botões: “Salvar” e “Ver Lista”

Tela de Lista de Medicamentos

Exibe todos os medicamentos cadastrados

Para cada item, mostra nome, dosagem e horário

Ações disponíveis:

Editar (abre o mesmo formulário de cadastro, pré-preenchido)

Marcar como "Tomado" (checkbox ou botão)

Excluir



