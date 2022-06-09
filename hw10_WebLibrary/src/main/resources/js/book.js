    getBooks()

    function outputCharacter(json) {
    if(document.getElementById("testid")){
    document.getElementsByTagName("table")[0].remove();
}
    if(document.getElementById("testName")){
    document.getElementsByTagName("form")[0].remove();
}
    var body = document.getElementsByTagName('body')[0];
    var table = document.createElement('table');
    var thead = document.createElement('thead');
    var tbody = document.createElement('tbody');
    thead.setAttribute('id','testid')
    thead.innerHTML =`
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Action</th>
                    <th>Action</th>
                </tr>`

    table.appendChild(thead);
    tbody.innerHTML=``
    json.forEach(book => tbody.innerHTML+=`
                <tr>
                  <td>${book.id}</td>
                  <td>${book.title}</td>
                  <td><button onclick = "deleteBooks(${book.id})">delete</button></td>
                  <td><button onclick = "editBooksPage(${book.id})">edit</button></td>
                  </tr>`);
    table.appendChild(thead);
    table.appendChild(tbody);
    body.appendChild(table)
}

    function editBooksPage(id){
    if(document.getElementById("testid")){
    document.getElementsByTagName("table")[0].remove();
}
    if(document.getElementById("testName")){
    document.getElementsByTagName("form")[0].remove();
}
    var body = document.getElementsByTagName('body')[0];
    var form = document.createElement('form');
    form.setAttribute('id','testName')
    form.innerHTML = `
        <div class="row">
            <label for="id-input">ID:</label>
            <input id="id-input" name ="id"type="text" readonly="readonly" value='${id}'>
        </div>
        <div class="row">
            <label name="lbl" value="Title">Title:</label>
            <input name="inp"/>
            <button type="submit">save</button>
        </div>
`
    body.appendChild(form);
    listener();

}

    //     function test(id){
//         console.log(id)
//
//         var body = document.getElementsByTagName('body')[0];
//         var form = document.createElement('form');
//         form.setAttribute('id','testName')
//         form.innerHTML = `
//         <div class="row">
//             <label for="id-input">ID:</label>
//             <input id="id-input" name ="id"type="text" readonly="readonly" value='${id}'>
//         </div>
//         <div class="row">
//             <label name="lbl" value="Title">Title:</label>
//             <input name="inp"/>
//             <button type="submit">save</button>
//         </div>
// `
//         body.appendChild(form);
//         listener();
//     }
//
//     function test2(){
//         if(document.getElementById('testName')){
//             var body = document.getElementsByTagName('body')[0];
//             body.innerHTML += `<label>label3</label>`
//         }
//
//     }



    function getBooks() {
        $.ajax({
            type: 'GET',
            url: '/api/books',
            success: (json) => {
                outputCharacter(json)
            }
        })
    }

    function deleteBooks(id) {

    $.ajax({
    type: 'DELETE',
    url: '/api/books/'+id,
}).done(function (){
    getBooks();
})
}
    function updateBooks(id,title){
    $.ajax({
    type: 'PUT',
    url: '/api/books/'+id +'/'+ title,
}).done(function (){
    getBooks();
})
}
    function serializeForm(formNode) {
    const elements = formNode.elements
    let arr = new Array();
    Array.from(elements)
    .forEach((element) => {
    arr.push(element.value)
})
    return arr
}

    async function handleFormSubmit(event) {
    event.preventDefault()
    const data = serializeForm(event.target)
    updateBooks(data[0],data[1])
}
    function listener(){
    const applicantForm = document.getElementById('testName')
    applicantForm.addEventListener('submit', handleFormSubmit)
}
