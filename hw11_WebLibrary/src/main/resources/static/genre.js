function getGenres() {
    $.ajax({
        type: 'GET',
        url: '/api/genres',
        success: (genres) => {
            allGenrePage(genres)
        }
    })
}

function allGenrePage(genres) {

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
                    <th>Action</th>
                </tr>`

    table.appendChild(thead);
    tbody.innerHTML = ``
    genres.forEach(genre => tbody.innerHTML += `
                <tr>
                  <td>${genre.id}</td>
                  <td>${genre.name}</td>
                  <td><button onclick = "deleteGenre('${genre.id}')">delete</button></td>
                  </tr>`);
    table.appendChild(thead);
    table.appendChild(tbody);
    body.appendChild(table)
}

function deleteGenre(id) {
    $.ajax({
        type: 'DELETE',
        url: '/api/genres/' + id,
    }).done(function () {
        getGenres()
    })
}