import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { NotaTaskDetailComponent } from 'app/entities/nota-task/nota-task-detail.component';
import { NotaTask } from 'app/shared/model/nota-task.model';

describe('Component Tests', () => {
  describe('NotaTask Management Detail Component', () => {
    let comp: NotaTaskDetailComponent;
    let fixture: ComponentFixture<NotaTaskDetailComponent>;
    const route = ({ data: of({ notaTask: new NotaTask(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [NotaTaskDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NotaTaskDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NotaTaskDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load notaTask on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.notaTask).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
