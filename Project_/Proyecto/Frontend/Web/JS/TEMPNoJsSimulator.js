  (function () {
    // Tu código aquí queda aislado del scope global
    const btn = document.getElementById('mi-boton');
    btn?.addEventListener('click', (e) => {
      e.preventDefault();
      console.log('Click controlado sin refresh');
    });
  })();