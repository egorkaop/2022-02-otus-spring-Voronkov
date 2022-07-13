function allBookPage(json) {
    console.log(json)
    if (document.getElementById("theadId")) {
        document.getElementsByTagName("table")[0].remove();
    }
    if (document.getElementById("form")) {
        document.getElementsByTagName("form")[0].remove();
    }
    if (document.getElementById("button")) {
        document.getElementById("button").remove();
    }
    var body = document.getElementsByTagName('body')[0];
    var table = document.createElement('table');
    var thead = document.createElement('thead');
    var tbody = document.createElement('tbody');
    thead.setAttribute('id', 'theadId')

    body.innerHTML += `
    <div>
    <button id="button" onclick = "getAuthorsAndGenres()">insert</button>
    </div>`
    thead.innerHTML = `
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Action</th>
                    <th>Action</th>
                    <th>Action</th>
                </tr>`

    table.appendChild(thead);
    tbody.innerHTML = ``
    json.forEach(book => tbody.innerHTML += `
                <tr>
                  <td>${book.id}</td>
                  <td>${book.title}</td>
                  <td><button onclick = "deleteBooks(${book.id})">delete</button></td>
                  <td><button onclick = "editBooksPage(${book.id})">edit</button></td>
                  <td><button onclick = "getFullBook(${book.id})">full information</button></td>
                  </tr>`);
    table.appendChild(thead);
    table.appendChild(tbody);
    body.appendChild(table)
}
function getAuthorsAndGenres(){
    $.ajax({
        type: 'GET',
        url: '/api/authors/',
        success: (authors) => {
            $.ajax({
                type: 'GET',
                url: '/api/genres/',
                success: (genres) => {
                    insertBookPage(authors,genres)
                }
            })
        }
    })
}
function insertBookPage(authors,genres){
    if (document.getElementById("theadId")) {
        document.getElementsByTagName("table")[0].remove();
    }
    if (document.getElementById("form")) {
        document.getElementsByTagName("form")[0].remove();
    }
    if (document.getElementById("button")) {
        document.getElementById("button").remove();
    }
    console.log(authors)
    console.log(genres)
    var body = document.getElementsByTagName('body')[0];
    var form = document.createElement('form');
    form.setAttribute('id', 'form')
    form.innerHTML = `
        <div class="row">
            <label name="lbl" value="Title">Title:</label>
            <input name="title"/>
            <button type="submit">save</button>
        </div>`
    var selectAuthors = document.createElement('select')
    selectAuthors.setAttribute('multiple','multiple')
    selectAuthors.setAttribute('name','authors')
    var selectGenres = document.createElement('select')
    selectGenres.setAttribute('multiple','multiple')
    selectGenres.setAttribute('name','genres')
    authors.forEach(author => selectAuthors.innerHTML += `
    <option value="${author.id}">${author.name} ${author.surname}</option>`)
    genres.forEach(genre => selectGenres.innerHTML += `
    <option value="${genre.id}">${genre.name}</option>`)
    var divAuthors = document.createElement('div')
    var divGenres = document.createElement('div')
    divAuthors.appendChild(selectAuthors)
    divGenres.appendChild(selectGenres)
    form.appendChild(divAuthors)
    form.appendChild(divGenres)
    body.appendChild(form);
    listenerInsert();
}
async function handleFormEditInsert(event) {
    event.preventDefault()
    const data = new FormData(event.target);
    const book = Object.fromEntries(data.entries());
    book.authors = data.getAll("authors");
    book.genres = data.getAll("genres");
    console.log(book)
    $.ajax({
        url: '/api/books',
        method: 'post',
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify(book),
        complete: function (){
            getBooks();
        }
    })
}

function listenerInsert() {
    const applicantForm = document.getElementById('form')
    applicantForm.addEventListener('submit', handleFormEditInsert)
}
function getFullBook(id){
    $.ajax({
        type: 'GET',
        url: '/api/books/' + id,
        success: (json) => {
            fullBookPage(json)
        }
    })
}

function fullBookPage(json){
    console.log(json)
    if (document.getElementById("theadId")) {
        document.getElementsByTagName("table")[0].remove();
    }
    if (document.getElementById("form")) {
        document.getElementsByTagName("form")[0].remove();
    }
    var body = document.getElementsByTagName('body')[0];
    var table = document.createElement('table');
    var thead = document.createElement('thead');
    var tbody = document.createElement('tbody');
    thead.setAttribute('id', 'theadId')
    var trHead = document.createElement('tr');
    var trBody = document.createElement('tr');
    trHead.innerHTML = `
                    <th>ID</th>
                    <th>Title</th>`
    json.authors.forEach(author => trHead.innerHTML+=`
    <th>Author</th>
`)
    json.genres.forEach(genre => trHead.innerHTML+=`
    <th>Genre</th>
`)
    trBody.innerHTML = `
    <td>${json.id}</td>
    <td>${json.title}</td>`
    json.authors.forEach(author => trBody.innerHTML+=`
    <td>${author.name} ${author.surname}</td>
`)
    json.genres.forEach(genre => trBody.innerHTML+=`
    <td>${genre.name}</td>
`)
    tbody.appendChild(trBody)
    thead.appendChild(trHead)
    table.appendChild(thead);
    table.appendChild(tbody);
    body.appendChild(table)
}

function editBooksPage(id) {

    if (document.getElementById("theadId")) {
        document.getElementsByTagName("table")[0].remove();
    }
    if (document.getElementById("form")) {
        document.getElementsByTagName("form")[0].remove();
    }
    var body = document.getElementsByTagName('body')[0];
    var form = document.createElement('form');
    form.setAttribute('id', 'form')
    form.innerHTML = `
        <div class="row">
            <label for="id-input">ID:</label>
            <input id="id-input" name ="id" type="text" readonly="readonly" value='${id}'>
        </div>
        <div class="row">
            <label name="lbl" value="Title">Title:</label>
            <input name="inp"/>
            <button type="submit">save</button>
        </div>
`
    body.appendChild(form);
    listenerEdit();

}

function getBooks() {
    $.ajax({
        type: 'GET',
        url: '/api/books',
        success: (json) => {
            allBookPage(json)
        }
    })
}

function deleteBooks(id) {

    $.ajax({
        type: 'DELETE',
        url: '/api/books/' + id,
    }).done(function () {
        getBooks();
    })
}

function updateBooks(id, title) {
    console.log(id)
    console.log(title)
    $.ajax({
        type: 'PATCH',
        url: '/api/books/' + id,
        dataType: 'json',
        data: title,
        complete: function (){
            getBooks();
        }
})}

function serializeFormEdit(formNode) {
    const elements = formNode.elements
    let arr = [];
    Array.from(elements)
        .forEach((element) => {
            arr.push(element.value)
        })
    return arr
}

async function handleFormEditSubmit(event) {
    event.preventDefault()
    event.preventDefault()
    const data = new FormData(event.target);
    const book = Object.fromEntries(data.entries());
    var object = {};
    object['title'] = book.inp;
    updateBooks(book.id, object)
}

function listenerEdit() {
    const applicantForm = document.getElementById('form')
    applicantForm.addEventListener('submit', handleFormEditSubmit)
}
