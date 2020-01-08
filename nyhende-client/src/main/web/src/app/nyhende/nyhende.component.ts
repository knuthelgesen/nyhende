import { Component, OnInit } from '@angular/core';
import {ContentService} from "../services/content.service";
import {ContentType} from "../model/content.model";

@Component({
  selector: 'app-nyhende',
  templateUrl: './nyhende.component.html',
  styleUrls: ['./nyhende.component.sass']
})
export class NyhendeComponent implements OnInit {

  constructor(private contentService: ContentService) { }

  contentType: ContentType;

  ngOnInit() {
    this.contentService.getContentType('/').subscribe(contentType => {
      this.contentType = contentType;
      console.log(contentType);
    }, error => {
      console.log(error);
    })
  }

}
