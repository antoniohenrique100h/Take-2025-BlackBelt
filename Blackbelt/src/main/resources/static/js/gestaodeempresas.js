const popup = document.getElementById("popup");
const openPopup = document.getElementById("openPopup");
const closePopup = document.getElementById("closePopup");

// Abre o pop-up quando o botão é clicado
openPopup.addEventListener("click", () => {
  popup.style.display = "block";
});

// Fecha o pop-up ao clicar no "X"
closePopup.addEventListener("click", () => {
  popup.style.display = "none";
});

// Fecha o pop-up ao clicar fora dele
window.addEventListener("click", (event) => {
  if (event.target == popup) {
    popup.style.display = "none";
  }
});

function openEditPopup(element) {
  // Recupera os dados a partir dos atributos data- do elemento
  const cnpj = element.getAttribute("data-cnpj");
  const nome = element.getAttribute("data-nome");
  const razaosocial = element.getAttribute("data-razaosocial");

  // Define os valores nos campos do formulário
  document.getElementById("editCnpj").value = cnpj;
  document.getElementById("editNome").value = nome;
  document.getElementById("editRazaoSocial").value = razaosocial;

  // Abre o pop-up de edição
  document.getElementById("popupEdit").style.display = "block";
}
// Função para fechar o pop-up de edição
document.getElementById("closePopupEdit").onclick = function () {
  document.getElementById("popupEdit").style.display = "none";
};
// Fechar o pop-up de edição se clicar fora dele
window.onclick = function (event) {
  const popupEdit = document.getElementById("popupEdit");
  if (event.target == popupEdit) {
    popupEdit.style.display = "none";
  }
};
