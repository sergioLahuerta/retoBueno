  function startBlinkCycle() {
    const elements = document.getElementsByClassName('');
    let blinkCount = 0;
    let isWhite = true;
  
    function toggleColor() {
      for (let i = 0; i < elements.length; i++) {
        elements[i].style.color = isWhite ? 'rgb(184, 182, 182)' : '#e22020';
      }
      isWhite = !isWhite;
    }
  
    function startBlinkCycle() {
      blinkCount = 0;
  
      const blinkInterval = setInterval(function () {
        toggleColor();
        blinkCount++;
  
        if (blinkCount >= 6) {
          clearInterval(blinkInterval);
          setTimeout(startBlinkCycle, 3000);
        }
      }, 200);
    }
  
    startBlinkCycle();
  };
  