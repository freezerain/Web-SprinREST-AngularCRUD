import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ApiService} from "../model/api.service";
import {LoadingState} from "../core/loading-state";

@Component({
  selector: 'app-progress-bar',
  templateUrl: './progress-bar.component.html',
  styleUrls: ['./progress-bar.component.css']
})
export class ProgressBarComponent implements OnInit {

  isVisible: boolean = false;
  private subscription: Subscription;

  constructor(private api: ApiService) {
  }

  ngOnInit() {
    this.subscription = this.api.loadingState
      .subscribe((state: LoadingState) => {
        this.isVisible = state.isLoading;
      });
  }

  //Prevent memory leak
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
