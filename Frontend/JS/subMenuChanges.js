/*Sección Submenú lateral (three-stripes)*/
document.addEventListener("DOMContentLoaded", () => {
    const toggleMenu = document.getElementById("toggle-menu");  
    toggleMenu.addEventListener("change", () => {
      if (toggleMenu.checked) {
        document.body.classList.add("no-scroll");
      } else {
        document.body.classList.remove("no-scroll");
      }
    });
    
    const checkbox = document.getElementById('toggle-menu');
    const imgHeaderRed = document.querySelector('.submenu');
    const imgHeader = document.querySelector('.img-icon-list-item');

    checkbox.addEventListener('change', () => {
      if (checkbox.checked) {
        imgHeader.style.display = 'none'
        imgHeaderRed.style.display = 'block';
      } else {
        imgHeader.style.display = 'block';
        imgHeaderRed.style.display = 'none';
      }
    });

  });

  