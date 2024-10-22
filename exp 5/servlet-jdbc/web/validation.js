/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



function handleOperationChange() {
    var operation = document.getElementById("operationSelect").value;
    var forms = document.querySelectorAll(".form-container");
    forms.forEach(function (form) {
        form.style.display = "none";
    });
    if (operation === "insert") {
        document.getElementById("insertForm").style.display = "block";
    } else if (operation === "displayIndividual") {
        document.getElementById("displayIndividualForm").style.display = "block";
    } else if (operation === "displayAll") {
        document.getElementById("displayAllForm").style.display = "block";
    } else if (operation === "update") {
        document.getElementById("updateForm").style.display = "block";
    } else if (operation === "delete") {
        document.getElementById("deleteForm").style.display = "block";
    }
}

function validateBookForm(event) {
    let valid = true;

    const bookName = event.target.book_name.value.trim();
    const author = event.target.author.value.trim();
    const publisher = event.target.publisher.value.trim();
    const edition = event.target.edition.value.trim();
    const price = event.target.price.value.trim();
    const category = event.target.category.value.trim();

    if (bookName === "" || author === "" || publisher === "" || edition === "" || category === "") {
        alert("All fields must be filled out");
        valid = false;
    }

    if (isNaN(price) || parseFloat(price) <= 0) {
        alert("Price must be a positive number");
        valid = false;
    }

    if (!valid) {
        event.preventDefault();
    }
}

function initializeLibraryManagement() {
    document.getElementById("operationSelect").onchange = handleOperationChange;
    document.getElementById("bookForm").onsubmit = validateBookForm;
    document.getElementById("updateBookForm").onsubmit = validateBookForm;
}

window.onload = initializeLibraryManagement;
