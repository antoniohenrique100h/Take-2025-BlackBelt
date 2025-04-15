const popup = document.getElementById("popup");
const openPopup = document.getElementById("openPopup");
const closePopup = document.getElementById("closePopup");

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
  const id = element.getAttribute("data-id");
  const login = element.getAttribute("data-login");
  const password = element.getAttribute("data-password");
  const cargo = element.getAttribute("data-cargo");
  const permissao = element.getAttribute("data-permissao");
  const cnpjempresa = element.getAttribute("data-empresa");

  // Define os valores nos campos do formulário
  document.getElementById("editId").value = id;
  document.getElementById("editLogin").value = login;
  document.getElementById("editPassword").value = password;
  document.getElementById("editCargo").value = cargo;
  document.getElementById("editPermissao").value = permissao;
  document.getElementById("editEmpresa").value = cnpjempresa;

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
