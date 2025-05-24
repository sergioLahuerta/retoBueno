function attachRestaurantEventListeners() {
  document.querySelectorAll(".find-your-restaurant-below-bar-container").forEach(container => {
    const triggerBtn = container.querySelector(".find-your-restaurant-below-bar-button");
    const panel = container.querySelector(".call-now-hidden-pannel");
    const cancelBtn = container.querySelector(".call-now-below-option.cancel");
    const copyBtn = container.querySelector(".call-now-below-option.copy");
    const numberEl = container.querySelector(".call-now-number");
    const copyMsg = container.querySelector(".copy-clipboard");

    if (!triggerBtn || !panel || !cancelBtn || !copyBtn || !numberEl || !copyMsg) return;

    triggerBtn.addEventListener("click", () => {
      panel.style.display = "block";
    });

    cancelBtn.addEventListener("click", () => {
      panel.style.display = "none";
    });

    copyBtn.addEventListener("click", () => {
      const phone = numberEl.innerText.trim();
      navigator.clipboard.writeText(phone).then(() => {
        numberEl.style.display = "none";
        copyMsg.style.display = "block";

        setTimeout(() => {
          copyMsg.style.display = "none";
          numberEl.style.display = "block";
          panel.style.display = "none";
        }, 4000);
      });
    });
  });
}
