import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { PrevisioneTaskUpdateComponent } from 'app/entities/previsione-task/previsione-task-update.component';
import { PrevisioneTaskService } from 'app/entities/previsione-task/previsione-task.service';
import { PrevisioneTask } from 'app/shared/model/previsione-task.model';

describe('Component Tests', () => {
  describe('PrevisioneTask Management Update Component', () => {
    let comp: PrevisioneTaskUpdateComponent;
    let fixture: ComponentFixture<PrevisioneTaskUpdateComponent>;
    let service: PrevisioneTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [PrevisioneTaskUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PrevisioneTaskUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrevisioneTaskUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrevisioneTaskService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PrevisioneTask(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new PrevisioneTask();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
