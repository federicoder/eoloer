import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { AssegnazioneTaskDetailComponent } from 'app/entities/assegnazione-task/assegnazione-task-detail.component';
import { AssegnazioneTask } from 'app/shared/model/assegnazione-task.model';

describe('Component Tests', () => {
  describe('AssegnazioneTask Management Detail Component', () => {
    let comp: AssegnazioneTaskDetailComponent;
    let fixture: ComponentFixture<AssegnazioneTaskDetailComponent>;
    const route = ({ data: of({ assegnazioneTask: new AssegnazioneTask(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [AssegnazioneTaskDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AssegnazioneTaskDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AssegnazioneTaskDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load assegnazioneTask on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.assegnazioneTask).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
