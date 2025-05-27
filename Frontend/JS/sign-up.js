/*Sign-Up*/
document.addEventListener('DOMContentLoaded', () => {
  const openBtn = document.getElementById('sing-up-button');
  const signupPanel = document.getElementById('signup-id');
  const closeBtn = document.getElementById('signup-close-btn');
  const backdrop = document.getElementById('background-grey');

  closeBtn.addEventListener('mouseover', () => {
    closeBtn.src = '../src/icons/crossRed.png';
  });

  closeBtn.addEventListener('mouseout', () => {
    closeBtn.src = '../src/icons/crossWhite.png';
  });

  openBtn.addEventListener('click', () => {
    signupPanel.classList.add('show');
    backdrop.classList.add('show');
    closeBtn.classList.add('show');
    document.body.classList.add('no-scroll');
  });

  const closeSignup = () => {
    signupPanel.classList.remove('show');
    backdrop.classList.remove('show');
    closeBtn.classList.remove('show');
    document.body.classList.remove('no-scroll');
  };

  closeBtn.addEventListener('click', closeSignup);
  backdrop.addEventListener('click', closeSignup);
});

/*Log-In*/
document.addEventListener('DOMContentLoaded', () => {
  const openBtn = document.getElementById('login-up-button');
  const signupPanel = document.getElementById('login-id');
  const closeBtn = document.getElementById('login-close-btn');
  const backdrop = document.getElementById('background-grey');

  closeBtn.addEventListener('mouseover', () => {
    closeBtn.src = '../src/icons/crossRed.png';
  });

  closeBtn.addEventListener('mouseout', () => {
    closeBtn.src = '../src/icons/crossWhite.png';
  });

  openBtn.addEventListener('click', () => {
    signupPanel.classList.add('show');
    backdrop.classList.add('show');
    closeBtn.classList.add('show');
    document.body.classList.add('no-scroll');
  });

  const closeSignup = () => {
    signupPanel.classList.remove('show');
    backdrop.classList.remove('show');
    closeBtn.classList.remove('show');
    document.body.classList.remove('no-scroll');
  };

  closeBtn.addEventListener('click', closeSignup);
  backdrop.addEventListener('click', closeSignup);
});
