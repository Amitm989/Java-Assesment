document.getElementById('applicationForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        dateOfBirth: document.getElementById('dateOfBirth').value,
        nationalInsuranceNumber: document.getElementById('nationalInsuranceNumber').value,
        residentialAddress: document.getElementById('residentialAddress').value,
        correspondenceAddress: document.getElementById('correspondenceAddress').value,
        bankAccountNumber: document.getElementById('bankAccountNumber').value,
        bankName: document.getElementById('bankName').value
    };

    fetch('/citizens', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => response.json())
        .then(data => {
            alert('Application submitted successfully!');
            document.getElementById('applicationForm').reset();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error submitting application.');
        });
});
