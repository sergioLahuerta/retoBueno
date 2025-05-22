  window.addEventListener('DOMContentLoaded', () => {
    const hash = window.location.hash;
    if (hash) {
      const checkbox = document.querySelector(hash);
      if (checkbox && checkbox.type === 'checkbox') {
        checkbox.checked = true;
      }
    }
  });