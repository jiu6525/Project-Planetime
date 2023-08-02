    const $sky = document.querySelector(".sky");

    window.onresize = () => {
      makeStars();
    }

    const makeStars = () => {
      const maxSize = Math.max(window.innerWidth, window.innerHeight)

      const getRandomX = () => Math.random() * maxSize;

      const getRandomY = () => Math.random() * maxSize;

      const randomRadius = () =>  Math.random() * 0.7 + 0.6;

      const _size = Math.floor(maxSize / 2);

      const htmlDummy = new Array(_size).fill().map((_, i) => {
        return  `<circle class='star'
            cx=${getRandomX()}
            cy=${getRandomY()}
            r=${randomRadius()}
            className="star" />`
      }).join('');

      $sky.innerHTML = htmlDummy;
    }

    window.onload = () => {
      makeStars();
    }