function toggleDropdown() {
      const dropdown = document.getElementById("myDropdown");
      dropdown.classList.toggle("show");
    }

    // Optional: close dropdown when clicking outside
    window.onclick = function(event) {
      if (!event.target.matches('.dropdown-toggle')) {
        const dropdowns = document.getElementsByClassName("dropdown-menu");

        for (let i = 0; i < dropdowns.length; i++) {
          dropdowns[i].classList.remove("show");
        }
      }
    }