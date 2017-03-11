

function showAddForm() {
	var x = document.getElementById('addBookDiv');

	var div1 = document.createElement('div');
	div1.setAttribute("id", "addFormDiv1");
	x.appendChild(div1);

	var div2 = document.createElement('div');
	div2.setAttribute("id", "addFormDiv2");
	div1.appendChild(div2);

	var createform = document.createElement('form:form'); // Create New
															// Element
	createform.setAttribute("commandName", "bookForm"); // Form
	createform.setAttribute("action", "addBook"); // Setting Action Attribute on Form
	createform.setAttribute("method", "post"); // Setting Method Attribute on
	// Form
	div2.appendChild(createform);

	var heading = document.createElement('h2'); // Heading of Form
	heading.innerHTML = "Add a book form ";
	createform.appendChild(heading);

	var line = document.createElement('hr'); // Giving Horizontal Row After
	// Heading
	createform.appendChild(line);

	var linebreak = document.createElement('br');
	createform.appendChild(linebreak);

	var namelabel = document.createElement('label'); // Create Label for Name
	// Field
	namelabel.innerHTML = "Title : "; // Set Field Labels
	createform.appendChild(namelabel);

	var inputelement = document.createElement('input'); // Create Input Field
	// for Name
	inputelement.setAttribute("type", "text");
	inputelement.setAttribute("name", "dname");
	createform.appendChild(inputelement);

	var linebreak = document.createElement('br');
	createform.appendChild(linebreak);

	var emaillabel = document.createElement('label'); // Create Label for
	// E-mail Field
	emaillabel.innerHTML = "Author : ";
	createform.appendChild(emaillabel);

	var emailelement = document.createElement('input'); // Create Input Field
	// for E-mail
	emailelement.setAttribute("type", "text");
	emailelement.setAttribute("name", "demail");
	createform.appendChild(emailelement);

	var emailbreak = document.createElement('br');
	createform.appendChild(emailbreak);

	var messagelabel = document.createElement('label'); // Append Textarea
	messagelabel.innerHTML = "Relase date : ";
	createform.appendChild(messagelabel);

	var texareaelement = document.createElement('textarea');
	texareaelement.setAttribute("name", "dmessage");
	createform.appendChild(texareaelement);

	var messagebreak = document.createElement('br');
	createform.appendChild(messagebreak);

	var submitelement = document.createElement('input'); // Append Submit
	// Button
	submitelement.setAttribute("type", "submit");
	submitelement.setAttribute("name", "dsubmit");
	submitelement.setAttribute("value", "Submit");
	createform.appendChild(submitelement);

}
