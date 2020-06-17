import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { AllegatoTemplateTaskDetailComponent } from 'app/entities/allegato-template-task/allegato-template-task-detail.component';
import { AllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';

describe('Component Tests', () => {
  describe('AllegatoTemplateTask Management Detail Component', () => {
    let comp: AllegatoTemplateTaskDetailComponent;
    let fixture: ComponentFixture<AllegatoTemplateTaskDetailComponent>;
    const route = ({ data: of({ allegatoTemplateTask: new AllegatoTemplateTask(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [AllegatoTemplateTaskDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AllegatoTemplateTaskDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AllegatoTemplateTaskDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load allegatoTemplateTask on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.allegatoTemplateTask).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
