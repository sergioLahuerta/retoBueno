document.addEventListener('DOMContentLoaded', () => {
  const banner = document.getElementById('cookie-banner');
  const acceptBtn = document.getElementById('accept-btn');
  const rejectBtn = document.getElementById('reject-btn');

  const cookiesDecision = localStorage.getItem('cookiesAccepted');

  if (!cookiesDecision) {
    banner.style.display = 'block';
  } else {
    banner.style.display = 'none';
  }

  acceptBtn.addEventListener('click', () => {
    localStorage.setItem('cookiesAccepted', 'true');
    banner.style.display = 'none';
    cargarAnalytics();
  });

  rejectBtn.addEventListener('click', () => {
    localStorage.setItem('cookiesAccepted', 'false');
    banner.style.display = 'none';
  });

  if (cookiesDecision === 'true') {
    cargarAnalytics();
  }

  function cargarAnalytics() {
    const ANALYTICS_ID = 'G-ABCD1234EF';

    if (!ANALYTICS_ID.startsWith('G-')) {
      console.warn('Analytics ID inválido:', ANALYTICS_ID);
      return;
    }

    const gaScript = document.createElement('script');
    gaScript.src = `https://www.googletagmanager.com/gtag/js?id=${ANALYTICS_ID}`;
    gaScript.async = true;
    gaScript.onerror = () => console.error("Fallo al cargar Google Analytics");
    document.head.appendChild(gaScript);

    window.dataLayer = window.dataLayer || [];
    function gtag() { dataLayer.push(arguments); }
    window.gtag = gtag;
    gtag('js', new Date());
    gtag('config', ANALYTICS_ID);
  }
});
