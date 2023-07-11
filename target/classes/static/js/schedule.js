// Get the buttons and forms
      const scheduleAppointmentBtn = document.getElementById('scheduleAppointmentBtn');
      const updateAppointmentBtn = document.getElementById('updateAppointmentBtn');
      const cancelAppointmentBtn = document.getElementById('cancelAppointmentBtn');
      const scheduleAppointmentForm = document.getElementById('scheduleAppointmentForm');
      const updateAppointmentForm = document.getElementById('updateAppointmentForm');
      const cancelAppointmentForm = document.getElementById('cancelAppointmentForm');

      // Add event listeners to the buttons
      scheduleAppointmentBtn.addEventListener('click', function () {
          showForm(scheduleAppointmentForm);
      });

      updateAppointmentBtn.addEventListener('click', function () {
          showForm(updateAppointmentForm);
      });

      cancelAppointmentBtn.addEventListener('click', function () {
          showForm(cancelAppointmentForm);
      });

      // Function to show a specific form and hide the others
      function showForm(form) {
          scheduleAppointmentForm.style.display = 'none';
          updateAppointmentForm.style.display = 'none';
          cancelAppointmentForm.style.display = 'none';

          form.style.display = 'block';
      }