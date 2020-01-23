'use strict';

let comicForm = document.getElementById('form');
let title =  document.getElementById('title');
let price = document.getElementById('price');
let pageCount = document.getElementById('page-count');
let rating = document.getElementById('rating');

let comicUri = 'http://localhost:8080/comicappv2/api/comics';

comicForm.addEventListener('submit', (event)=>{
  //we need to prevent default form behaviour
  event.preventDefault();
  sendcomic();
  });

async function sendcomic() {
  let comic = {};
  comic.id = 0;
  comic.title = title.value;
  comic.price = price.value;
  comic.pageCount = pageCount.value;
  comic.rating = rating.value;

  console.log(comic);

  let response = await fetch(comicUri, { method: 'POST', body: JSON.stringify(comic) });
  console.log(response);
  let body = await response.text();
  console.log(`Response Body: ${body}`);
}
