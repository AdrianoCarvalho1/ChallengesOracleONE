let amigos= [];

function atualizarListaAmigos() {
    let lista = document.getElementById("listaAmigos");

    lista.innerHTML = "";

    for (let i = 0; i < amigos.length; i++) {
        let li = document.createElement("li");

        li.textContent = amigos[i];
        lista.appendChild(li);
    }
}

function sortearAmigo() {
    if (amigos.length == 0) {
        alert("Digite o nome dos seus amigos para realizar o sorteio.");
    } else {
        let indiceAleatorio = Math.floor(Math.random() * amigos.length);
        let amigoSorteado = amigos[indiceAleatorio];

        document.getElementById("resultado").innerHTML = amigoSorteado;
    }
}

function adicionarAmigo() {
    let campoAmigo = document.getElementById("amigo").value.trim();

    if (campoAmigo == "") {
        alert("Por favor, insira um nome.");
    } else {
        amigos.push(campoAmigo);
        document.getElementById("amigo").value = "";
        
        atualizarListaAmigos();
    }
}