import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { AllegatoTaskDetailComponent } from 'app/entities/allegato-task/allegato-task-detail.component';
import { AllegatoTask } from 'app/shared/model/allegato-task.model';

describe('Component Tests', () => {
  describe('AllegatoTask Management Detail Component', () => {
    let comp: AllegatoTaskDetailComponent;
    let fixture: ComponentFixture<AllegatoTaskDetailComponent>;
    const route = ({ data: of({ allegatoTask: new AllegatoTask(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [AllegatoTaskDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AllegatoTaskDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AllegatoTaskDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load allegatoTask on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.allegatoTask).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
