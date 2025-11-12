## 🚀 Como Começar

Siga os passos abaixo para ter uma cópia local do projeto em execução na sua máquina.

### Pré-requisitos

Você precisará ter instalado na sua máquina:

* **Java Development Kit (JDK) 8** ou superior.
* Uma IDE compatível com projetos Java (ex: **IntelliJ IDEA**, **Eclipse**, **VS Code** com extensões Java).

---

### Instalação e Execução

#### 1. Clonar o Repositório

Abra o seu terminal ou prompt de comando e execute o seguinte:

```bash
git clone [https://github.com/dev-academico/clinica-vet-LP2.git](https://github.com/dev-academico/clinica-vet-LP2.git)"
```
#### 2. Importar o Projeto

1.  Abra a sua **IDE** de preferência (ex: IntelliJ IDEA, Eclipse).
2.  Selecione a opção para **Abrir** ou **Importar Projeto** (*Open/Import Project*).
3.  Selecione a pasta `clinica-vet-LP2` que você acabou de clonar. A IDE deve reconhecer automaticamente a estrutura do projeto Java.

#### 3. Executar a Aplicação

1.  Navegue até o diretório `src` dentro da estrutura do projeto na sua IDE.
2.  Localize a **classe principal** (a classe que contém o método `public static void main(String[] args)`).
3.  **Clique com o botão direito** na classe principal e selecione a opção **Executar** (*Run*). O console da aplicação deverá ser iniciado.

### Linha de comando

Se você achar melhor utilizar linha de comando, recomendamos o seguinte

1. Abra a sua IDE (como o Codespaces do GitHub), e execute o seguinte comando no terminal (atualização para a versão 21):
```sudo apt update && sudo apt install -y openjdk-21-jdk```

2. Depois, você pode seguir com a compilação e execução. Primeiro, coloque-os numa pasta para os .class correspondentes a cada um dos arquivos .java:

```/usr/lib/jvm/java-21-openjdk-amd64/bin/javac -d bin src/**/*.java```
```/usr/lib/jvm/java-21-openjdk-amd64/bin/java -cp bin Main```

*Caso a sua IDE tenha uma versão bem recente da JVM, talvez não seja necessário seguir o passo 1, sendo necessário apenas compilar e executar (sem recorrer aos endereçamentos e ao superusuário com ```sudo```)*








