function editBook(bookID, title, authorId, authorName, authorSurname,
		published, isbn) {

	var createdForm = document.createElement('form');
	createdForm.setAttribute("class", "form-inline");
	createdForm.setAttribute("commandName", "bookCommandForm");
	createdForm.setAttribute("action", "editBook");
	createdForm.setAttribute("method", "post");

	var trToEdit = document.getElementById(bookID);
	trToEdit.innerHTML = '';
	var tdColSpan = document.createElement('td');
	tdColSpan.setAttribute("colspan", "5");
	trToEdit.appendChild(tdColSpan);
	tdColSpan.appendChild(createdForm);

	var div = document.createElement('div');
	var div1 = document.createElement('div');
	div1.setAttribute("style", "float:left; width:20%")
	var div2 = document.createElement('div');
	div2.setAttribute("style", "float:left; width:20%")
	var div3 = document.createElement('div');
	div3.setAttribute("style", "float:left; width:20%")
	var div4 = document.createElement('div');
	div4.setAttribute("style", "float:left; width:20%")
	var div5 = document.createElement('div');
	div5.setAttribute("style", "float:left; width:20%")

	createdForm.appendChild(div);
	div.appendChild(div1);
	div.appendChild(div2);
	div.appendChild(div3);
	div.appendChild(div4);
	div.appendChild(div5);

	// title

	var inputTitle = document.createElement('input');
	inputTitle.setAttribute("name", "title");
	inputTitle.setAttribute("type", "text");
	inputTitle.setAttribute("class", "form-control");
	inputTitle.setAttribute("value", title);

	div1.appendChild(inputTitle);

	// author name

	div2.innerHTML = authorName + ' ' + authorSurname;

	// relase date

	var inputDate = document.createElement('input');
	inputDate.setAttribute("name", "published");
	inputDate.setAttribute("type", "text");
	inputDate.setAttribute("class", "form-control");
	inputDate.setAttribute("value", published);

	div3.appendChild(inputDate);

	// isbn

	var inputIsbn = document.createElement('input');
	inputIsbn.setAttribute("name", "isbn");
	inputIsbn.setAttribute("type", "text");
	inputIsbn.setAttribute("class", "form-control");
	inputIsbn.setAttribute("value", isbn);

	div4.appendChild(inputIsbn);
	// id-hidden input

	var inputId = document.createElement('input');
	inputId.setAttribute("name", "id");
	inputId.setAttribute("type", "hidden");
	inputId.setAttribute("value", bookID);

	createdForm.appendChild(inputId);

	// authorId-hidden input

	var inputAuthorId = document.createElement('input');
	inputAuthorId.setAttribute("name", "authorId");
	inputAuthorId.setAttribute("type", "hidden");
	inputAuthorId.setAttribute("value", authorId);

	createdForm.appendChild(inputAuthorId);

	// button

	var submitButton = document.createElement('input');
	submitButton.setAttribute("type", "submit");
	submitButton.setAttribute("class", "btn btn-success btn-xs");
	submitButton.setAttribute("value", "Done");
	div5.appendChild(submitButton);
}