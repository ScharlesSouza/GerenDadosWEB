//executa uma função no atributo onclick do botão
document.getElementById("botao").onclick=()=>
{
    //coloca os elementos do documento html em variaveis
    let texto = document.getElementsByTagName("input")[0].value;
    let lista = document.getElementById("lista");
    //cria o elementos do tipo item de lista <li> em variavel (memoria em tempo de execução)
    var li = document.createElement("li");
    //cria o elementos do tipo texto a partir do que estava escrito no campo input   
    var noTexto = document.createTextNode(texto);
    //adiciona o elemento "noTexto" como filho do elemento "li"
    li.appendChild(noTexto);
    lista.appendChild(li);

    var botaoEditar = document.createElement("input");
    botaoEditar.setAttribute("type", "button");
    botaoEditar.setAttribute("value", "editar");
    botaoEditar.onclick = editar;
    li.appendChild(botaoEditar);

    var botaoRemover = document.createElement("input");
    botaoRemover.setAttribute("type", "button");
    botaoRemover.setAttribute("value", "remover");
    //executa uma função no atributo onclick do botão
    botaoRemover.onclick = remover;
    li.appendChild(botaoRemover);
    
}

//()=>{} arrowFunction identifica que a função é chamada pela janela que esta em execução
//function(){} identifica que a função é chamada por um elemento especifico
let remover = function(){
    var pai = this.parentNode;
    pai.parentNode.removeChild(pai);

}

let editar = function(){

    var pai = this.parentNode;

    var cxTexto = document.createElement("input");
    cxTexto.setAttribute("type", "text");
    cxTexto.setAttribute("id","textEditar")
    pai.appendChild(cxTexto);

    var botaoSalvar = document.createElement("input");
    botaoSalvar.setAttribute("type", "button");
    botaoSalvar.setAttribute("value", "salvar");
    //executa uma função no atributo onclick do botão
    botaoSalvar.onclick = salvar;
    pai.appendChild(botaoSalvar);

}

let salvar = function(){

    
    //coloca os elementos do documento html em variaveis
    let texto = document.getElementById("textEditar").value;
    let lista = document.getElementById("lista");
    //cria o elementos do tipo item de lista <li> em variavel (memoria em tempo de execução)
    var li = document.createElement("li");
    //cria o elementos do tipo texto a partir do que estava escrito no campo input   
    var noTexto = document.createTextNode(texto);
    //adiciona o elemento "noTexto" como filho do elemento "li"
    li.appendChild(noTexto);
    
    var botaoEditar = document.createElement("input");
    botaoEditar.setAttribute("type", "button");
    botaoEditar.setAttribute("value", "editar");
    botaoEditar.onclick = editar;
    li.appendChild(botaoEditar);

    var botaoRemover = document.createElement("input");
    botaoRemover.setAttribute("type", "button");
    botaoRemover.setAttribute("value", "remover");
    //executa uma função no atributo onclick do botão
    botaoRemover.onclick = remover;
    li.appendChild(botaoRemover);
    
    var pai = this.parentNode;
    pai.parentNode.replaceChild(li, pai);

}