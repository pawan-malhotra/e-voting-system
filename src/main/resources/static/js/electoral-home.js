let showDetails = document.getElementsByClassName('fa-solid');

for (let index = 0; index < showDetails.length; index++) {
    showDetails[index].addEventListener('click' , function(e) {
        e.preventDefault();
        const cardContent = document.getElementsByClassName('card-content');
        cardContent[index].classList.toggle('show');
    })
    
}