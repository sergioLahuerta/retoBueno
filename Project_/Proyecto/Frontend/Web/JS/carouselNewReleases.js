function inicializarCarrusel() {
  const carruseles = document.querySelectorAll(".carrusel");
  const progressBars = document.querySelectorAll(".carousel-progress-bar");
  const progressContainers = document.querySelectorAll(".carousel-progress-container");

  carruseles.forEach((carrusel, index) => {
    const progressBar = progressBars[index];
    const progressContainer = progressContainers[index];

    const thumbWidth = 40;
    let isDragging = false;
    let autoScrollInterval;
    const moveAmount = 200;

    progressBar.style.width = `${thumbWidth}px`;
    progressBar.style.position = "absolute";
    progressContainer.style.position = "relative";

    const isLandscape = () => window.matchMedia("(orientation: landscape)").matches;

    function autoScrollCarousel() {
      if (!isLandscape()) return;

      const maxScroll = carrusel.scrollWidth - carrusel.clientWidth;
      const currentScroll = carrusel.scrollLeft;
      const remainingScroll = maxScroll - currentScroll;

      let nextScroll = remainingScroll <= moveAmount ? maxScroll : currentScroll + moveAmount;

      carrusel.scrollTo({ left: nextScroll, behavior: 'smooth' });

      if (nextScroll === maxScroll) {
        setTimeout(() => carrusel.scrollTo({ left: 0, behavior: 'smooth' }), 4000);
      }

      updateThumbPosition();
    }

    function updateThumbPosition() {
      const maxScroll = carrusel.scrollWidth - carrusel.clientWidth;
      const currentScroll = carrusel.scrollLeft;
      const containerSize = progressContainer.clientWidth;
      const maxThumbPos = containerSize - thumbWidth;
      const scrollPercent = maxScroll > 0 ? currentScroll / maxScroll : 0;
      const thumbPos = scrollPercent * maxThumbPos;
      progressBar.style.left = `${thumbPos}px`;
    }

    carrusel.addEventListener("scroll", updateThumbPosition);

    progressBar.addEventListener("mousedown", (e) => {
      if (isLandscape()) return;
      isDragging = true;
      e.preventDefault();
    });

    window.addEventListener("mouseup", () => isDragging = false);

    window.addEventListener("mousemove", (e) => {
      if (!isDragging || isLandscape()) return;

      const containerRect = progressContainer.getBoundingClientRect();
      const containerSize = progressContainer.clientWidth;
      const maxThumbPos = containerSize - thumbWidth;
      const newPos = e.clientX - containerRect.left - thumbWidth / 2;
      const clampedPos = Math.max(0, Math.min(newPos, maxThumbPos));

      progressBar.style.left = `${clampedPos}px`;

      const maxScroll = carrusel.scrollWidth - carrusel.clientWidth;
      const scrollPercent = clampedPos / maxThumbPos;

      carrusel.scrollLeft = scrollPercent * maxScroll;
    });

    function restartAutoScroll() {
      clearInterval(autoScrollInterval);
      if (isLandscape()) {
        autoScrollInterval = setInterval(autoScrollCarousel, 4000);
      }
    }

    window.addEventListener("resize", restartAutoScroll);
    window.addEventListener("orientationchange", () => setTimeout(restartAutoScroll, 200));

    updateThumbPosition();
    restartAutoScroll();
  });
}
