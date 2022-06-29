function getAuthors() {
    $.ajax({
        type: 'GET',
        url: '/api/authors',
        success: (authors) => {
            allAuthorPage(authors)
        }
    })
}

function allAuthorPage(authors) {

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
    thead.innerHTML = `
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Action</th>
                </tr>`

    table.appendChild(thead);
    tbody.innerHTML = ``
    authors.forEach(author => tbody.innerHTML += `
                <tr>
                  <td>${author.id}</td>
                  <td>${author.name}</td>
                  <td>${author.surname}</td>
                  <td><button onclick = "deleteAuthor('${author.id}')">delete</button></td>
                  </tr>`);
    table.appendChild(thead);
    table.appendChild(tbody);
    body.appendChild(table)
}

function deleteAuthor(id) {
    $.ajax({
        type: 'DELETE',
        url: '/api/authors/' + id,
    }).done(function () {
        getAuthors()
    })
}